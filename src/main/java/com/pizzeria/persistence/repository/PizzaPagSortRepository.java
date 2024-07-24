package com.pizzeria.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.pizzeria.persistence.entity.Pizza;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<Pizza, Integer> {
  Page<Pizza> findByAvailableTrue(Pageable pageable);
}
