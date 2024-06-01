package com.shop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.model.Drink;
import com.shop24.repository.DrinkRepository;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

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

   
}
