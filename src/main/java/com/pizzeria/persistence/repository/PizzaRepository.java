package com.pizzeria.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.pizzeria.persistence.entity.Pizza;
import com.pizzeria.service.dto.UpdatePizzaPrice;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{
  List<Pizza> findAllByAvailableTrueOrderByPrice();
  Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);
  List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
  List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
  int countByVeganTrue();
  List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

  @Query(value = """
      UPDATE pizza
      SET price = :#{#newPizzaPrice.newPrice}
      WHERE id_pizza = :#{#newPizzaPrice.pizzaId}""", nativeQuery = true)
  @Modifying
  void updatePrice(@Param("newPizzaPrice") UpdatePizzaPrice newPizzaPrice);
}
