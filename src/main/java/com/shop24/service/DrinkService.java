package com.shop24.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.dto.DrinkConsumptionDTO;
import com.shop24.model.Drink;
import com.shop24.repository.DrinkRepository;
import com.shop24.repository.OrderRepository;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Drink getDrinkById(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    public Drink createDrink(Drink drink) {
        // Check if a drink with the same name already exists
        if (drinkRepository.existsByName(drink.getName())) {
            throw new IllegalArgumentException("A drink with the same name already exists.");
        }
        return drinkRepository.save(drink);
    }

    public List<Drink> getAllAvailableDrinks() {
        return drinkRepository.findByQuantityGreaterThan(0);
    }

    public List<DrinkConsumptionDTO> getMostConsumedDrinks() {
        List<Object[]> results = orderRepository.findMostConsumedDrinks();
        return results.stream()
            .map(result -> new DrinkConsumptionDTO((String) result[0], (Long) result[1]))
            .collect(Collectors.toList());
    }
}
