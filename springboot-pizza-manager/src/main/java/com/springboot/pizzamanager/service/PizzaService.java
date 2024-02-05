package com.springboot.pizzamanager.service;

import com.springboot.pizzamanager.model.Pizza;
import com.springboot.pizzamanager.model.Topping;
import com.springboot.pizzamanager.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> findAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }

    public Pizza addPizza(Pizza pizza) {
        // Additional logic to ensure no duplicate pizzas can be added
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new IllegalStateException("Pizza with id " + id + " does not exist.");
        }
        pizzaRepository.deleteById(id);
    }

    public Pizza updatePizza(Long id, Pizza updatedPizza) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Pizza with id " + id + " does not exist."
                ));

        String newName = updatedPizza.getName();
        if (newName != null && newName.length() > 0 && !pizza.getName().equals(newName)) {
            pizza.setName(newName);
        }
        // Consider how to handle topping updates here

        return pizzaRepository.save(pizza);
    }

    public Pizza addToppingToPizza(Long pizzaId, Topping topping) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pizza with id " + pizzaId + " does not exist."
                ));
        pizza.addTopping(topping);
        return pizzaRepository.save(pizza);
    }

    public Pizza removeToppingFromPizza(Long pizzaId, Topping topping) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new IllegalStateException(
                        "Pizza with id " + pizzaId + " does not exist."
                ));
        pizza.removeTopping(topping);
        return pizzaRepository.save(pizza);
    }
}
