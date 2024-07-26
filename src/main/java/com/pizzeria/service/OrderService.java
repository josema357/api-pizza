package com.pizzeria.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Order;
import com.pizzeria.persistence.projection.OrderSummary;
import com.pizzeria.persistence.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
  private final OrderRepository orderRepository;

  private static final String DELIVERY = "D";
  private static final String CARRYOUT = "C";
  private static final String ON_SITE = "S";

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

  public List<Order> getTodayOrders(){
    try {
      LocalDateTime today = LocalDate.now().atTime(0,0);
      return this.orderRepository.findAllByDateAfter(today);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public List<Order> getOutsideOrders(){
    try {
      List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
      return this.orderRepository.findAllByMethodIn(methods);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public List<Order> getCustomerOrders(String idCustomer){
    try {
      return this.orderRepository.findCustomerOrders(idCustomer);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public OrderSummary getSummary(int orderId){
    return this.orderRepository.findSummary(orderId);
  }
}
