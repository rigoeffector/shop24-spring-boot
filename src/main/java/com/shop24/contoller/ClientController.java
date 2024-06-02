package com.shop24.contoller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop24.model.CargoCompany;
import com.shop24.model.Client;
import com.shop24.service.CargoCompanyService;
import com.shop24.service.ClientService;
import com.shop24.util.DistanceCalculator;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private CargoCompanyService cargoCompanyService;
    
    @GetMapping
    public ResponseEntity<Object> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_ALL_CLIENTS, true, clients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_CLIENT_WITH_ID + id, true, client));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseBuilder.buildResponse(Shop24APIMessages.CLIENT_NOT_FOUND, false, null));
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
        try {
            Client createClient = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.ADD_CLIENT_SUCCESS, true, createClient));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }
    
    @GetMapping("/cargo-companies/nearest")
    public ResponseEntity<Object> getNearestCargoCompanies(@RequestParam double latitude, @RequestParam double longitude, @RequestParam int limit) {
        List<CargoCompany> cargoCompanies = cargoCompanyService.getAllCargoCompanies();
        cargoCompanies.forEach(cargoCompany -> {
            double distance = DistanceCalculator.distance(latitude, longitude, cargoCompany.getLatitude(), cargoCompany.getLongitude());
            cargoCompany.setDistance(distance);
        });

        // Sort cargo companies by distance
        List<CargoCompany> nearestCargoCompanies = cargoCompanies.stream()
                .sorted(Comparator.comparing(CargoCompany::getDistance))
                .limit(limit)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(ResponseBuilder.buildResponse(Shop24APIMessages.NEAREST_CARGO_COMPANIES_SUCCESS, true, nearestCargoCompanies));
    }
}