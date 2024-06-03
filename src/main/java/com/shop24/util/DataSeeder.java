package com.shop24.util;

import com.shop24.model.Drink;
import com.shop24.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    @Autowired
    private DrinkRepository drinkRepository;

    public void seedData() {
        // Check if drink with the same name exists before adding
        if (!drinkRepository.existsByName("Cola")) {
            Drink drink1 = new Drink(null, "Cola", "Description 1", 1.99, 100);
            drinkRepository.save(drink1);
        }

        if (!drinkRepository.existsByName("Lemonade")) {
            Drink drink2 = new Drink(null, "Lemonade", "Description 2", 2.99, 200);
            drinkRepository.save(drink2);
        }
        
        if (!drinkRepository.existsByName("Orange Juice")) {
            Drink drink2 = new Drink(null, "Orange Juice", "Description 2", 2.99, 200);
            drinkRepository.save(drink2);
        }

        // Add more drinks as needed
    }
}
