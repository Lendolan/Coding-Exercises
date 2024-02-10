package com.springboot.pizzamanager.dto;
import java.util.List;

public class PizzaUpdateRequest {
    private String name;
    private List<Long> toppingIds; // Assuming toppings are identified by their ID

    public PizzaUpdateRequest() {
    }

    public PizzaUpdateRequest(String name, List<Long> toppingIds) {
        this.name = name;
        this.toppingIds = toppingIds;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Long> getToppingIds() {
        return toppingIds;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setToppingIds(List<Long> toppingIds) {
        this.toppingIds = toppingIds;
    }
}
