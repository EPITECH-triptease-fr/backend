package com.epitech.triptease.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenProvider {

    @Value("${triptease.jwt.secret}")
    private String jwtSecret;

    @Value("${triptease.jwt.jwtExpireMinutes}")
    private Long jwtExpireMinutes;

    private Algorithm jwtAlgorithm;

    @PostConstruct()
    public void init() {
        this.jwtAlgorithm = Algorithm.HMAC512(jwtSecret);
    }


    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        return JWT
                .create()
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(SecurityConstants.JWT_ROLES_NAMESPACE, userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withClaim(SecurityConstants.TOKEN_ID, userPrincipal.getId())
                .withClaim(SecurityConstants.TOKEN_MAIL, userPrincipal.getEmail())
                .withExpiresAt(Date.from(LocalDateTime.now().plus(Duration.ofMinutes(jwtExpireMinutes)).atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return decodeJwt(token).getSubject();
    }

    public Boolean validateJwtToken(String jwt) {
        JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
        try {
            verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public DecodedJWT decodeJwt(String jwt) {
        return JWT.decode(jwt);
    }
}