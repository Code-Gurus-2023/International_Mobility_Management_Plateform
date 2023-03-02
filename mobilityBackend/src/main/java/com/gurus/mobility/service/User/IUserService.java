package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    User getUserByUsername(String username);
}

