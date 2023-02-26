package com.gurus.mobility.security.jwt;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private  String secret;
}
