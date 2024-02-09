package com.springboot.pizzamanager.repository;

import com.springboot.pizzamanager.model.Topping;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
	boolean existsByName(String name);
}