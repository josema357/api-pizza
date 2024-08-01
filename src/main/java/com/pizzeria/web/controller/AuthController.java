package com.pizzeria.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.service.AuthService;
import com.pizzeria.service.dto.Login;
import com.pizzeria.web.config.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
  private final AuthService authService;
  private final JwtUtils jwtUtils;
  
  public AuthController(AuthService authService, JwtUtils jwtUtils) {
    this.authService = authService;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody Login loginDTO){
    Authentication authentication = this.authService.login(loginDTO);
    log.info("Authenticated : {}", authentication.isAuthenticated());
    log.info("Authenticated user : {}", authentication.getPrincipal());
    String jwt = this.jwtUtils.create(loginDTO.username());
    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
  }
}
