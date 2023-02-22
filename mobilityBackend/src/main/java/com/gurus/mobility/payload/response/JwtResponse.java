package com.gurus.mobility.payload.response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String identifiant;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Integer id,String identifiant, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.identifiant = identifiant;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
}
