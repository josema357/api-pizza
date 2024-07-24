package com.pizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.pizzeria.persistence.entity.Order;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
  
}
