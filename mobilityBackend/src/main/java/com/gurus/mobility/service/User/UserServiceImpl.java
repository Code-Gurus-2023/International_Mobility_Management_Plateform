package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.exception.UserNotFoundException;
import com.gurus.mobility.repository.User.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private final UserRepository userRepository;


    private PasswordEncoder passwordEncoder;
    private RestTemplate restTemplate;


    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByIdentifiant(String identifiant) {
        return userRepository.getUserByIdentifiant(identifiant);
    }
    @Override
    public Boolean hasAccount(String identifiant) {
        User user = this.getUserByIdentifiant(identifiant);
        return this.getUserByIdentifiant(identifiant).getHasAccount();
    }

    @Override
    public User ActivateAccount(Boolean isActive, String identifiant) {
        User user = this.getUserByIdentifiant(identifiant);
        user.setActive(isActive);
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User add(User user) {
       user.setUserName(user.getEmail());
        //user.setHasAccount(true);
        return userRepository.save(user);
    }


    @SneakyThrows
    public User getUserByID(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found" + id));
        return user;
    }

    public User addUser(User user) {
        user.setUserName(user.getEmail());
       // user.setHasAccount(true);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User updateuser, String identifiant) {
            User user = userRepository.findByIdentifiant(identifiant);
            userRepository.save(user);
            return user;
    }


    private static String storageDirectoryPath = System.getProperty("user.dir") + "/images/";

    @Override
    public String uploadImage(MultipartFile file) {
        makeDirectoryIfNotExist(storageDirectoryPath);
        Path storageDirectory = Paths.get(storageDirectoryPath);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/images/getImage/")
                .path(fileName)
                .toUriString();
        //return the download image url as a response entity
        String imageLink = destination.toString();
        return imageLink;
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(Integer userId) {

        userRepository.deleteById(userId);

    }

}
