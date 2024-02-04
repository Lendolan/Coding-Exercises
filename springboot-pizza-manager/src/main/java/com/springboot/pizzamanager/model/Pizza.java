package com.springboot.pizzamanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    
    @ManyToMany
    private Set<Topping> toppings;
    
 // Constructors
    public Pizza() {}

    public Pizza(String name) {
        this.name = name;
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
    
    public void addTopping(Topping topping) {
        toppings.add(topping);
        topping.getPizzas().add(this);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
        topping.getPizzas().remove(this);
    }
    
}
