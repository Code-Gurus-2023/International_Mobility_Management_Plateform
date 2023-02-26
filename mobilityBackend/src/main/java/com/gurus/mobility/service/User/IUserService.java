package com.gurus.mobility.service.User;

import com.gurus.mobility.entity.user.User;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface IUserService {

    public Boolean hasAccount(String identifiant);
    public User ActivateAccount(Boolean isActive, String identifiant);
    public List<User> getAllUsers();
    public User add(User user);

    User getUserByID(Integer id);
    User getUserByIdentifiant(String identifiant);
    User getUserByEmail(String email);

    User addUser(User user);

    User updateUser(User updateuser, String identifiant);


    String uploadImage(MultipartFile file);

    User getUserByUserName(String userName);


    void deleteUser(Integer userId);


}

