package com.pizzeria.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.pizzeria.persistence.entity.UserEntity;
import com.pizzeria.persistence.entity.UserRole;
import com.pizzeria.persistence.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
  private final UserRepository userRepository;
  
  public UserSecurityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = this.userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("User:"+username+" not found"));
    String[] roles = user.getRoles().stream().map(UserRole::getRole).toArray(String[]::new);
    return User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .roles(roles)
      .accountLocked(user.getLocked())
      .disabled(user.getDisabled())
      .build();
  }
  
}
