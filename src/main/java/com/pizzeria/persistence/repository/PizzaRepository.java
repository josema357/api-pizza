package com.pizzeria.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.pizzeria.persistence.entity.Pizza;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer>{
  List<Pizza> findAllByAvailableTrueOrderByPrice();
  Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);
  List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
  List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
  int countByVeganTrue();
  List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

}
