package com.pizzeria.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pizzeria.persistence.entity.Pizza;
import com.pizzeria.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
  private final PizzaRepository pizzaRepository;

  /**
   * Constructor-based injection 
   * No necessary @Autowired annotation because of the single constructor
   * @param jdbcTemplate template
   */
  public PizzaService(PizzaRepository pizzaRepository){
    this.pizzaRepository = pizzaRepository;
  }

  public List<Pizza> getAll(){
    return this.pizzaRepository.findAll();
  }

  public Pizza get(int id_pizza){
    return this.pizzaRepository.findById(id_pizza).orElse(null);
  }
}
