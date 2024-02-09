package com.springboot.pizzamanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
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
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "pizza_toppings", 
      joinColumns = @JoinColumn(name = "pizza_id"), 
      inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private Set<Topping> toppings = new HashSet<>();
    
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
    
    
    // Methods to add and remove toppings
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }
    
    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }
    
    public Set<Topping> getToppings() {
        return this.toppings;
    }
    
}
