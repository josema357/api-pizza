package com.pizzeria.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzeria.persistence.entity.Pizza;
import com.pizzeria.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
  private final PizzaService pizzaService;

  /**
   * Constructor-based injection 
   * No necessary @Autowired annotation because of the single constructor
   * @param pizzaService service
   */
  public PizzaController(PizzaService pizzaService){
    this.pizzaService = pizzaService;
  }

  @GetMapping
  public ResponseEntity<List<Pizza>> getAll(){
    return ResponseEntity.ok(this.pizzaService.getAll());
  }

  @GetMapping("/{idPizza}")
  public ResponseEntity<Pizza> get(@PathVariable int idPizza){
    return ResponseEntity.ok(this.pizzaService.get(idPizza));
  }

  @GetMapping("/available")
  public ResponseEntity<List<Pizza>> getAvailable(){
    return ResponseEntity.ok(this.pizzaService.getAvailable());
  }
  @GetMapping("/available/{name}")
  public ResponseEntity<Pizza> getByName(@PathVariable String name){
    return ResponseEntity.ok(this.pizzaService.getByName(name));
  }
  @GetMapping("/available/with/{description}")
  public ResponseEntity<List<Pizza>> getWith(@PathVariable String description){
    return ResponseEntity.ok(this.pizzaService.getWith(description));
  }
  @GetMapping("/available/without/{description}")
  public ResponseEntity<List<Pizza>> getWithout(@PathVariable String description){
    return ResponseEntity.ok(this.pizzaService.getWithout(description));
  }
  @GetMapping("/cheapest/{price}")
  public ResponseEntity<List<Pizza>> getCheapest(@PathVariable double price){
    return ResponseEntity.ok(this.pizzaService.getCheapest(price));
  }

  @PostMapping
  public ResponseEntity<Pizza> add(@RequestBody Pizza pizza){
    if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
      return ResponseEntity.ok(this.pizzaService.save(pizza));
    }
    return ResponseEntity.badRequest().build();
  }

  @PutMapping
  public ResponseEntity<Pizza> update(@RequestBody Pizza pizza){
    if(pizza.getIdPizza() != null || this.pizzaService.exists(pizza.getIdPizza())){
      return ResponseEntity.ok(this.pizzaService.save(pizza));
    }
    return ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/{idPizza}")
  public ResponseEntity<Void> delete(@PathVariable int idPizza){
    if(this.pizzaService.exists(idPizza)){
      this.pizzaService.delete(idPizza);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }
}
