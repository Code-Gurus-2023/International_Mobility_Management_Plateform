package com.gurus.mobility.service;

import com.gurus.mobility.entity.User;
import com.gurus.mobility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    public UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
