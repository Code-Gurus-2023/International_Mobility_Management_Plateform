package com.gurus.mobility.dto;

import com.gurus.mobility.entity.user.User;


public class UserTokenDto {

    private String token;
    private User userEntity;

    public UserTokenDto(String token, User userEntity) {
        this.token = token;
        this.userEntity = userEntity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(User userEntity) {
        this.userEntity = userEntity;
    }
}
