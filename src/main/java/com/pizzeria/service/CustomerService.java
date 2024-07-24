package com.pizzeria.service;

import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Customer;
import com.pizzeria.persistence.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  public Customer findByPhone(String phone){
    try {
      return this.customerRepository.findByPhoneNumber(phone);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }
}
