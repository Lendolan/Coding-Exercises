package com.springboot.pizzamanager.dto;

import java.util.Set;

public class PizzaResponse {
    private Long id;
    private String name;
    private Set<ToppingDTO> toppings; // Set of topping names

    // Default constructor
    public PizzaResponse() {
    }

    // Constructor with all fields
    public PizzaResponse(Long id, String name, Set<ToppingDTO> toppings) {
        this.id = id;
        this.name = name;
        this.toppings = toppings;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ToppingDTO> getToppings() {
        return toppings;
    }

    public void setToppings(Set<ToppingDTO> toppings) {
        this.toppings = toppings;
    }
}
