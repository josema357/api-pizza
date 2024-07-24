package com.pizzeria.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.persistence.entity.Order;
import com.pizzeria.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService){
    this.orderService = orderService;
  }
  
  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders(){
    return ResponseEntity.ok(this.orderService.getAll());
  }
}
