package com.pizzeria.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.pizzeria.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

  
}