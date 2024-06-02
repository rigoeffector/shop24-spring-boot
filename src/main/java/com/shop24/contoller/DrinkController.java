package com.shop24.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shop24.model.Drink;
import com.shop24.service.DrinkService;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping
    public ResponseEntity<Object> getAllDrinks() {
        List<Drink> drinks = drinkService.getAllDrinks();
        return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ALL_DRINKS, true, drinks));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDrinkById(@PathVariable Long id) {
        Drink drink = drinkService.getDrinkById(id);
        if (drink != null) {
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_DRINK_WITH_ID  + id, true, drink));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseBuilder.buildResponse(Shop24APIMessages.DRINK_NOT_FOUND, false, null));
        }
    }
    
    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> createDrink(@RequestBody Drink drink) {
        try {
            Drink createdDrink = drinkService.createDrink(drink);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ADD_DRINK_SUCCESS , true, createdDrink));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
    
//    @GetMapping("/most-consumed/{limit}")
//    public ResponseEntity<Object> getMostConsumedDrinks(@PathVariable int limit) {
//        try {
//            List<Object[]> mostConsumedDrinks = drinkService.getMostConsumedDrinks(limit);
//            // You might want to format the response as per your requirement
//            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ALL_DRINKS, true, mostConsumedDrinks));
//        } catch (IllegalArgumentException e) {
//        	   return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                       .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
//        }
//    }


  
}