package com.pizzeria.web.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.annotation.PostConstruct;

@Component
public class JwtUtils {
  @Value("${SECRET_KEY}")
  private String SECRET_KEY;

  private Algorithm ALGORITHM;

  @PostConstruct
  public void init() {
    this.ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
  }

  public String create(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuer("pizzeria")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITHM);
  }

  public boolean validate(String jwt){
    try {
      JWT.require(ALGORITHM)
        .build()
        .verify(jwt);
      return true;
    } catch (JWTVerificationException e) {
      return false;
    }
  }

  public String getUsername(String jwt){
    return JWT.require(ALGORITHM)
      .build()
      .verify(jwt)
      .getSubject();
  }
}
