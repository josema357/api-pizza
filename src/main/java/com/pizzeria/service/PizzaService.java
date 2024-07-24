package com.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Pizza;
import com.pizzeria.persistence.repository.PizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PizzaService {
  private final PizzaRepository pizzaRepository;

  /**
   * Constructor-based injection
   * No necessary @Autowired annotation because of the single constructor
   * 
   * @param jdbcTemplate template
   */
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public List<Pizza> getAll() {
    try {
      return this.pizzaRepository.findAll();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public List<Pizza> getAvailable(){
    try {
      int count = this.pizzaRepository.countByVeganTrue();
      log.info("Count : {}", count);
      return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public Pizza getByName(String name){
    try {
      return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()->new RuntimeException("La pizza no existe"));
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }
  public List<Pizza> getWith(String name){
    try {
      return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(name);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }
  public List<Pizza> getWithout(String name){
    try {
      return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(name);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public List<Pizza> getCheapest(Double price){
    try {
      return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public Pizza get(int id_pizza) {
    try {
      return this.pizzaRepository.findById(id_pizza).orElse(null);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public Pizza save(Pizza pizza) {
    try {
      return this.pizzaRepository.save(pizza);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public void delete(int idPizza){
    try {
      this.pizzaRepository.deleteById(idPizza);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public boolean exists(int idPizza) {
    return this.pizzaRepository.existsById(idPizza);
  }
}
