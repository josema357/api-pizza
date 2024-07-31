package com.pizzeria.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.persistence.entity.Customer;
import com.pizzeria.persistence.entity.Order;
import com.pizzeria.service.CustomerService;
import com.pizzeria.service.OrderService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;
  private final OrderService orderService;

  public CustomerController(CustomerService customerService, OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  @GetMapping("/phone/{phone}")
  public ResponseEntity<Customer> getByPhone(@PathVariable String phone){
    return ResponseEntity.ok(this.customerService.findByPhone(phone));
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String id){
    return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
  }
}
