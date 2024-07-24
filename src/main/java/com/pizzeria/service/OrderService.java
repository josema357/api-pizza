package com.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Order;
import com.pizzeria.persistence.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  public List<Order> getAll(){
    try {
      return this.orderRepository.findAll();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }
}
