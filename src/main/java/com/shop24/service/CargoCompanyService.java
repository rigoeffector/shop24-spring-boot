package com.shop24.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.model.CargoCompany;
import com.shop24.model.Drink;
import com.shop24.model.Order;
import com.shop24.repository.CargoCompanyRepository;
import com.shop24.repository.OrderRepository;
import com.shop24.util.DistanceCalculator;

@Service
public class CargoCompanyService {

    @Autowired
    private CargoCompanyRepository cargoCompanyRepository;
    @Autowired OrderRepository orderRepository;

  

    public CargoCompany createCargoCompany(CargoCompany cargoCompany) {
        if (cargoCompanyRepository.existsByName(cargoCompany.getName())) {
            throw new IllegalArgumentException("Cargo Company already exists.");
        }
        return cargoCompanyRepository.save(cargoCompany);
    }

    public CargoCompany getCargoCompanyById(Long id) {
        return cargoCompanyRepository.findById(id).orElse(null);
    }

    public List<CargoCompany> getAllCargoCompanies() {
        return cargoCompanyRepository.findAll();
    }

    
    public List<CargoCompany> findNearestCargoCompanies(double clientLat, double clientLon, double radius) {
        return cargoCompanyRepository.findAll().stream()
            .filter(cargo -> DistanceCalculator.distance(clientLat, clientLon, cargo.getLatitude(), cargo.getLongitude()) <= radius)
            .collect(Collectors.toList());
    }
    
    
    public List<Drink> getDrinksTransportedByCargoCompany(Long cargoCompanyId, LocalDate startDate, LocalDate endDate) {
        CargoCompany cargoCompany = getCargoCompanyById(cargoCompanyId);
        if (cargoCompany == null) {
            throw new IllegalArgumentException("Cargo company not found.");
        }

        List<Order> orders = orderRepository.findByCargoCompanyAndCreatedAtBetween(cargoCompany, startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        return orders.stream()
                     .flatMap(order -> order.getDrinks().stream())
                     .distinct()
                     .collect(Collectors.toList());
    }
   

}
