package com.pizzeria.persistence.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.pizzeria.persistence.entity.Order;
import java.time.LocalDateTime;


public interface OrderRepository extends ListCrudRepository<Order, Integer> {
  List<Order> findAllByDateAfter(LocalDateTime date);
  List<Order> findAllByMethodIn(List<String> methods);
}
