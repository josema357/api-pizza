package com.pizzeria.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.persistence.entity.Order;
import com.pizzeria.persistence.projection.OrderSummary;
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

  @GetMapping("/today")
  public ResponseEntity<List<Order>> getTodayOrders(){
    return ResponseEntity.ok(this.orderService.getTodayOrders());
  }

  @GetMapping("/outside")
  public ResponseEntity<List<Order>> getOutsideOrders(){
    return ResponseEntity.ok(this.orderService.getOutsideOrders());
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<List<Order>> getOutsideOrders(@PathVariable String id){
    return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
  }

  @GetMapping("/summary/{id}")
  public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable int id){
    return ResponseEntity.ok(this.orderService.getSummary(id));
  }
}
