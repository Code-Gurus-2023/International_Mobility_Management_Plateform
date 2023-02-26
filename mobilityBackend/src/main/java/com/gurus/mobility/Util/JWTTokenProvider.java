package com.gurus.mobility.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gurus.mobility.entity.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secret;


    //Token generation with date expiration , whith issuer for more description and sign with algorithm
    public String generateJwtToken(UserPrincipal userPrincipal){

        String[] claims = getClaimsfromUser(userPrincipal);

        return JWT.create().withIssuer("ogagatewayafrica").withAudience("administration")
                .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
                .withArrayClaim("authorities",claims).withExpiresAt(new Date(System.currentTimeMillis() + 432_000_000))
                .sign(Algorithm.HMAC512(secret.getBytes()));

    }

    //Get authorities from token
    //Récupérer les autorites de l'utilisateur
    public String[] getClaimsfromUser(UserPrincipal userPrincipal){
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);

    }
}
