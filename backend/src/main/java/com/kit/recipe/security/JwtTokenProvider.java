package com.kit.recipe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kit.recipe.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;



    public String generateToken(String userEmail) {
        Instant now = Instant.now();
        Instant expirationDate = now.plus(7, ChronoUnit.DAYS);

        return JWT.create()
                .withSubject(userEmail)
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getUserMailFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token).getSubject();
    }

    public String generateToken(Authentication authentication) {

        Users user = (Users) authentication.getPrincipal();
        return generateToken(user.getEmail());
    }


    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecret)).build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Invalid Token");
        }
        return false;

    }
}
