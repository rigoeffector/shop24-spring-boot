package com.shop24.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.model.CargoCompany;
import com.shop24.model.Drink;
import com.shop24.repository.CargoCompanyRepository;
import com.shop24.util.DistanceCalculator;

@Service
public class CargoCompanyService {

    @Autowired
    private CargoCompanyRepository cargoCompanyRepository;

  

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
    
   

//    public List<Drink> getDrinksTransportedByDateRangeAndCargoCompany(LocalDate startDate, LocalDate endDate, CargoCompany cargoCompany) {
//        return cargoCompanyRepository.findDrinksTransportedByDateRangeAndCargoCompany(startDate, endDate, cargoCompany);
//    }
//    
}
