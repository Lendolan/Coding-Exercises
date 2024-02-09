package com.springboot.pizzamanager.dto;

import java.util.Set;

public class PizzaRequest {
    private String name;
    private Set<Long> toppingIds; // A set of topping IDs

    // Default constructor
    public PizzaRequest() {
    }

    // Constructor with parameters
    public PizzaRequest(String name, Set<Long> toppingIds) {
        this.name = name;
        this.toppingIds = toppingIds;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getToppingIds() {
        return toppingIds;
    }

    public void setToppingIds(Set<Long> toppingIds) {
        this.toppingIds = toppingIds;
    }
}
