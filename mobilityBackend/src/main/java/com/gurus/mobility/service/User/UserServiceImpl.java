package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.Accomodation.Accomodation;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UserNotFoundException;
import com.gurus.mobility.repository.AccomodationRepository.AccomodationRepository;
import com.gurus.mobility.repository.User.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ServletContext context;
    @Autowired
    private AccomodationRepository accomodationRepository;


    @Override
    public User getUserByIdentifiant(String identifiant) {
        return userRepository.getUserByIdentifiant(identifiant);
    }


    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User addUser(User user) {
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setVerified(false);
        return userRepository.save(user);
    }


    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found" + id));
        return user;
    }

    @Override
    public User updateUser(User updateuser, Long idUser) {
        User user = userRepository.findById(idUser).get();
        user.setUserName(updateuser.getUserName());
        user.setEmail(updateuser.getEmail());
        user.setLocation(updateuser.getLocation());
        user.setPhoneNumber(updateuser.getPhoneNumber());
        user.setExperienceYears(updateuser.getExperienceYears());
        user.setProfessorDiploma(updateuser.getProfessorDiploma());
        user.setStudentLevel(updateuser.getStudentLevel());
        user.setStudentSpeciality(updateuser.getStudentSpeciality());
        user.setProfileImage(updateuser.getProfileImage());
        user.setPassword(bCryptPasswordEncoder.encode(updateuser.getPassword()));
        userRepository.save(user);
        return user;
    }


    private static String storageDirectoryPath = System.getProperty("user.dir") + "/images/";

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


    @Override
    public String verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || (user.getVerified() == true)) {
            return "verify_fail";
        } else {
            user.setVerificationCode(null);
            user.setVerified(true);
            userRepository.save(user);

            return "verify_success";
        }
    }

    @Override
    public String forgotPassword(String email) {

        Optional<User> userOptional = Optional
                .ofNullable(userRepository.findByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getToken();
    }

    @Override
    public String resetPassword(String token, String password) {

        Optional<User> userOptional = Optional
                .ofNullable(userRepository.findByToken(token));

        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        User user = userOptional.get();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }

    public String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    public boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

    @Override
    public String Verified(Long idUser) {
        User user = userRepository.findById(idUser).get();
        if (user.getVerified() == false)
        {user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);
        return "User successfuly Activated!"; }
        else
            user.setVerified(false);
        userRepository.save(user);
        return "User successfuly Desactivated!";

    }

    @Override
    public User getUserByIdAndRole(Long id) {
        return userRepository.findUserByIdAndRole(id);
    }
    @Override
    public User affecterAccToOwner(Long idAcc, Long idUser) {
        Accomodation accomodation=accomodationRepository.findById(idAcc)
                .orElse(null);
        User user=userRepository.findUserByIdAndRole(idUser);
        user.getAccomodations().add(accomodation);
        return userRepository.save(user);
    }




}
