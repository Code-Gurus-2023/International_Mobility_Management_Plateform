package com.gurus.mobility.dto;

import com.gurus.mobility.entity.user.User;


public class UserCreationDto {

    private User user;

    private String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
