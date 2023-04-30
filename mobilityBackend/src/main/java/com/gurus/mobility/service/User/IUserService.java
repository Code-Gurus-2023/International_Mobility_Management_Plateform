package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.User;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

import java.util.Optional;


public interface IUserService {

    public List<User> getAllUsers();

    User getUserByIdentifiant(String identifiant);

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(User updateuser, Long idUser);;

    String verify(String verificationCode);

    String forgotPassword(String email);

    String resetPassword(String token, String password);

    String Verified(Long idUser);

    User getUserByUserName(String username);
    /**
     * Developed by sidaoui
     **/
    public User getUserByIdAndRole(Long id);

    public User affecterAccToOwner(Long idAcc,Long idUser);


    public User findById(Long id);

    public User getUserByUsername(String username);

    int NBClaimsLastDate (LocalDateTime date, Long userid);
}

