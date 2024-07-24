package com.pizzeria.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.persistence.entity.Customer;
import com.pizzeria.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/phone/{phone}")
  public ResponseEntity<Customer> getByPhone(@PathVariable String phone){
    return ResponseEntity.ok(this.customerService.findByPhone(phone));
  }
}
