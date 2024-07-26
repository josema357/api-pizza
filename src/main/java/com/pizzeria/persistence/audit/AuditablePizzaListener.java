package com.pizzeria.persistence.audit;

import org.springframework.util.SerializationUtils;

import com.pizzeria.persistence.entity.Pizza;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditablePizzaListener {
  private Pizza currentPizza;

  @PostLoad
  private void postLoad(Pizza entity){
    System.out.println("POST LOAD");
    this.currentPizza = SerializationUtils.clone(entity);
  }

  @PostPersist
  @PostUpdate
  public void onPostPersist(Pizza entity){
    System.out.println("OLD VALUE :" + this.currentPizza);
    System.out.println("NEW VALUE :" + entity);
  }

  @PreRemove
  public void onPreDelete(Pizza entity){
    System.out.println(entity);
  }
}
