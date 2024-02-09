package com.springboot.pizzamanager.controller;

import com.springboot.pizzamanager.model.Topping;
import com.springboot.pizzamanager.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/toppings")
public class ToppingController {

    private final ToppingService toppingService;

    @Autowired
    public ToppingController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @GetMapping
    public List<Topping> getAllToppings() {
        return toppingService.findAllToppings();
    }

    @PostMapping
    public Topping addTopping(@RequestBody Topping topping) {
        return toppingService.addTopping(topping);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopping(@PathVariable Long id) {
        toppingService.deleteTopping(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Topping updateTopping(@PathVariable Long id, @RequestBody Topping topping) {
        return toppingService.updateTopping(id, topping);
    }
}
