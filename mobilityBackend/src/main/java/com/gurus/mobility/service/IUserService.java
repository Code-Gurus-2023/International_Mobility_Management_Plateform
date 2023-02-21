package com.gurus.mobility.service;

import com.gurus.mobility.entity.user.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers();
    public User updateUser(User user);
    public User add(User user);
    public User findById(Integer id);
}
