package com.pizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.pizzeria.persistence.entity.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{
  
}
