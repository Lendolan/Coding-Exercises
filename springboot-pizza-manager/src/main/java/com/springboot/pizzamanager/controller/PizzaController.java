package com.springboot.pizzamanager.controller;

import com.springboot.pizzamanager.model.Pizza;
import com.springboot.pizzamanager.model.Topping;
import com.springboot.pizzamanager.service.PizzaService;
import com.springboot.pizzamanager.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;
    private final ToppingService toppingService;

    @Autowired
    public PizzaController(PizzaService pizzaService, ToppingService toppingService) {
        this.pizzaService = pizzaService;
        this.toppingService = toppingService;
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.findAllPizzas();
    }

    @PostMapping
    public Pizza addPizza(@RequestBody Pizza pizza) {
        return pizzaService.addPizza(pizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable Long id) {
        pizzaService.deletePizza(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        return pizzaService.updatePizza(id, pizza);
    }

    // Endpoint to add a topping to a pizza
    @PostMapping("/{pizzaId}/toppings/{toppingId}")
    public Pizza addToppingToPizza(@PathVariable Long pizzaId, @PathVariable Long toppingId) {
        Topping topping = toppingService.findToppingById(toppingId)
                .orElseThrow(() -> new IllegalArgumentException("Topping not found"));
        return pizzaService.addToppingToPizza(pizzaId, topping);
    }

}
