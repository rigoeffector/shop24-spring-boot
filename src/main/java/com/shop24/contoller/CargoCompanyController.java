package com.shop24.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop24.model.CargoCompany;
import com.shop24.service.CargoCompanyService;

@RestController
@RequestMapping("/api/cargo-companies")
public class CargoCompanyController {
    @Autowired
    private CargoCompanyService cargoCompanyService;

    @GetMapping
    public List<CargoCompany> getAllCargoCompanies() {
        return cargoCompanyService.getAllCargoCompanies();
    }

    @GetMapping("/{id}")
    public CargoCompany getCargoCompanyById(@PathVariable Long id) {
        return cargoCompanyService.getCargoCompanyById(id);
    }

    // Add other endpoints for the required functionalities
}