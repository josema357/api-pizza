package com.pizzeria.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.pizzeria.persistence.entity.Customer;

public interface CustomerRepository extends ListCrudRepository<Customer, String>{
  @Query(value = "SELECT c FROM Customer c WHERE c.phoneNumber = :phone")
  Customer findByPhoneNumber(@Param("phone") String phone);
}
