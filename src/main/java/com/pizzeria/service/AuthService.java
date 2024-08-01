package com.pizzeria.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pizzeria.service.dto.Login;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
  private final AuthenticationManager authenticationManager;
  
  public AuthService(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public Authentication login(Login dto){
    log.info("Login method");
    UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
    return this.authenticationManager.authenticate(login);
  }
}
