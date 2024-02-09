package com.springboot.pizzamanager.dto;

public class ToppingDTO {
    private Long id;
    private String name;

    // Default constructor
    public ToppingDTO() {
    }

    // Constructor with parameters
    public ToppingDTO(Long id, String name) {
        this.id = id;
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

    // toString method for debugging
    @Override
    public String toString() {
        return "ToppingDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

