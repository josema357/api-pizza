package com.pizzeria.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Pizza;
import com.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.pizzeria.persistence.repository.PizzaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PizzaService {
  private final PizzaRepository pizzaRepository;
  private final PizzaPagSortRepository pizzaPagSortRepository;

  /**
   * Constructor-based injection
   * No necessary @Autowired annotation because of the single constructor
   * 
   * @param jdbcTemplate template
   */
  public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
    this.pizzaRepository = pizzaRepository;
    this.pizzaPagSortRepository = pizzaPagSortRepository;
  }

  public Page<Pizza> getAll(int page, int elements) {
    try {
      Pageable pageRequest = PageRequest.of(page, elements);
      return this.pizzaPagSortRepository.findAll(pageRequest);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  public Page<Pizza> getAvailable(int page, int elements, String sortBy, String sortDirection){
    try {
      int count = this.pizzaRepository.countByVeganTrue();
      log.info("Count : {}", count);
      Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
      Pageable pageRequest = PageRequest.of(page, elements, sort);
      return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
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
