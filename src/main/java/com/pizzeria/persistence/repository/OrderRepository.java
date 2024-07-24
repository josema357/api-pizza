package com.pizzeria.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.pizzeria.persistence.entity.Order;
import java.time.LocalDateTime;


public interface OrderRepository extends ListCrudRepository<Order, Integer> {
  List<Order> findAllByDateAfter(LocalDateTime date);
  List<Order> findAllByMethodIn(List<String> methods);

  @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
  List<Order> findCustomerOrders(@Param("id") String idCustomer);
}
