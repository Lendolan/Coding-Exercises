package com.springboot.pizzamanager.repository;

import com.springboot.pizzamanager.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	boolean existsByName(String name);
}
