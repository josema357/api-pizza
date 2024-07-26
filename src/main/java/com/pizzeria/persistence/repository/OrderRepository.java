package com.pizzeria.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.pizzeria.persistence.entity.Order;
import com.pizzeria.persistence.projection.OrderSummary;

import java.time.LocalDateTime;


public interface OrderRepository extends ListCrudRepository<Order, Integer> {
  List<Order> findAllByDateAfter(LocalDateTime date);
  List<Order> findAllByMethodIn(List<String> methods);

  @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
  List<Order> findCustomerOrders(@Param("id") String idCustomer);

  @Query(value = """
                SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate,
                    po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames
                FROM pizza_order po 
                    INNER JOIN customer cu ON po.id_customer = cu.id_customer
                    INNER JOIN order_item oi ON po.id_order = oi.id_order
                    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza
                WHERE po.id_order = :orderId
                GROUP BY po.id_order, cu.name, po.date, po.total
                """, nativeQuery = true)
  OrderSummary findSummary(@Param("orderId") int orderId);

  @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
  boolean saveRandomOrder(@Param("id_customer")String idCustomer,@Param("method") String method);
}
