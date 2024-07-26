package com.pizzeria.persistence.entity;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pizzeria.persistence.audit.Auditable;
import com.pizzeria.persistence.audit.AuditablePizzaListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, AuditablePizzaListener.class})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pizza extends Auditable implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pizza", nullable = false)
  private Integer idPizza;
  @Column(nullable = false, length = 30, unique = true)
  private String name;
  @Column(nullable = false, length = 150)
  private String description;
  @Column(nullable = false, columnDefinition = "Decimal(5,2)")
  private Double price;
  @Column(columnDefinition = "TINYINT")
  private Boolean vegetarian;
  @Column(columnDefinition = "TINYINT")
  private Boolean vegan;
  @Column(columnDefinition = "TINYINT", nullable = false)
  private Boolean available;


}
