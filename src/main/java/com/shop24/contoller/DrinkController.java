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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.shop24.dto.CargoCompanyDTO;
import com.shop24.dto.DrinkConsumptionDTO;
import com.shop24.dto.DrinkDTO;
import com.shop24.model.CargoCompany;
import com.shop24.model.Client;
import com.shop24.model.Drink;
import com.shop24.service.CargoCompanyService;
import com.shop24.service.ClientService;
import com.shop24.service.DrinkService;
import com.shop24.util.DTOMapper;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CargoCompanyService cargoCompanyService;

    @GetMapping
    public ResponseEntity<Object> getAllDrinks() {
        List<Drink> drinks = drinkService.getAllDrinks();
        List<DrinkDTO> drinkDTOs = drinks.stream().map(DTOMapper::toDrinkDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ALL_DRINKS, true, drinkDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDrinkById(@PathVariable Long id) {
        Drink drink = drinkService.getDrinkById(id);
        if (drink != null) {
            DrinkDTO drinkDTO = DTOMapper.toDrinkDTO(drink);
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_DRINK_WITH_ID + id, true, drinkDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseBuilder.buildResponse(Shop24APIMessages.DRINK_NOT_FOUND, false, null));
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> createDrink(@RequestBody Drink drink) {
        try {
            Drink createdDrink = drinkService.createDrink(drink);
            DrinkDTO drinkDTO = DTOMapper.toDrinkDTO(createdDrink);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ADD_DRINK_SUCCESS, true, drinkDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @GetMapping("/client/{clientId}/available-drinks-and-nearest-cargo")
    public ResponseEntity<Object> getAvailableDrinksAndNearestCargo(@PathVariable Long clientId) {
        try {
            Client client = clientService.getClientById(clientId);
            List<Drink> availableDrinks = drinkService.getAllAvailableDrinks();
            List<CargoCompany> nearestCargoCompanies = cargoCompanyService.findNearestCargoCompanies(client.getLatitude(), client.getLongitude(), 3.0);

            List<DrinkDTO> drinkDTOs = availableDrinks.stream().map(DTOMapper::toDrinkDTO).collect(Collectors.toList());
            List<CargoCompanyDTO> cargoCompanyDTOs = nearestCargoCompanies.stream().map(DTOMapper::toCargoCompanyDTO).collect(Collectors.toList());

            Map<String, Object> responseData = Map.of(
                "availableDrinks", drinkDTOs,
                "nearestCargoCompanies", cargoCompanyDTOs
            );

            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_AVAILABLE_DRINKS_AND_NEAREST_CARGO, true, responseData));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @GetMapping("/most-consumed-drinks")
    public ResponseEntity<Object> getMostConsumedDrinks() {
        try {
            List<DrinkConsumptionDTO> mostConsumedDrinks = drinkService.getMostConsumedDrinks();
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_MOST_CONSUMED_DRINKS, true, mostConsumedDrinks));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
}
