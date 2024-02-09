package com.springboot.pizzamanager.service;

import com.springboot.pizzamanager.model.Topping;
import com.springboot.pizzamanager.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToppingService {

    private final ToppingRepository toppingRepository;

    @Autowired
    public ToppingService(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    public List<Topping> findAllToppings() {
        return toppingRepository.findAll();
    }

    public Optional<Topping> findToppingById(Long id) {
        return toppingRepository.findById(id);
    }

    @Transactional
    public Topping addTopping(Topping topping) {
        if (toppingRepository.existsByName(topping.getName())) {
            throw new IllegalStateException("Topping already exists.");
        }
        return toppingRepository.save(topping);
    }

    @Transactional
    public void deleteTopping(Long id) {
        if (!toppingRepository.existsById(id)) {
            throw new IllegalStateException("Topping with id " + id + " does not exist.");
        }
        toppingRepository.deleteById(id);
    }

    @Transactional
    public Topping updateTopping(Long id, Topping updatedTopping) {
        Topping topping = toppingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Topping with id " + id + " does not exist."
                ));

        String newName = updatedTopping.getName();
        if (newName != null && newName.length() > 0 && !topping.getName().equals(newName)) {
            topping.setName(newName);
        }
        

        return toppingRepository.save(topping);
    }
}
