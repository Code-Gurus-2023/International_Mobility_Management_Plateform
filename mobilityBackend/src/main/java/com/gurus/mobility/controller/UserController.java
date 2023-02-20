package com.gurus.mobility.controller;

import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.UserRepository;
import com.gurus.mobility.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController  {

    @Autowired
    public IUserService userService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/listUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User add(@RequestBody User user){
        userService.add(user);
        return user;
    }

}
