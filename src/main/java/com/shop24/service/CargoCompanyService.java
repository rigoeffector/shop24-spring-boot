package com.shop24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop24.model.CargoCompany;
import com.shop24.repository.CargoCompanyRepository;

@Service
public class CargoCompanyService {
    @Autowired
    private CargoCompanyRepository cargoCompanyRepository;

    public List<CargoCompany> getAllCargoCompanies() {
        return cargoCompanyRepository.findAll();
    }

    public CargoCompany getCargoCompanyById(Long id) {
        return cargoCompanyRepository.findById(id).orElse(null);
    }

    // Add other methods for the required functionalities
}
