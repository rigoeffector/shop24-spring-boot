package com.shop24.contoller;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop24.dto.CargoCompanyDTO;
import com.shop24.model.CargoCompany;
import com.shop24.model.Drink;
import com.shop24.service.CargoCompanyService;
import com.shop24.util.DTOMapper;
import com.shop24.util.ResponseBuilder;
import com.shop24.util.Shop24APIMessages;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
//import org.springframework.format.annotation.DateTimeFormat;

@RestController
@Tag(name = "get", description = "List Cargo Companies")
@RequestMapping("/api/cargocompanies")
public class CargoCompanyController {

    @Autowired
    private CargoCompanyService cargoCompanyService;

    @PostMapping
    public ResponseEntity<Object> createCargoCompany(@RequestBody CargoCompany cargoCompany) {
        try {
            CargoCompany createdCargoCompany = cargoCompanyService.createCargoCompany(cargoCompany);
            CargoCompanyDTO cargoCompanyDTO = DTOMapper.toCargoCompanyDTO(createdCargoCompany);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.CARGO_COMPANY_CREATED_SUCCESS, true, cargoCompanyDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
        }
    }

    @Tag(name = "get", description = "GET methods of Employee APIs")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCargoCompanyById(@PathVariable Long id) {
        CargoCompany cargoCompany = cargoCompanyService.getCargoCompanyById(id);
        if (cargoCompany != null) {
            CargoCompanyDTO cargoCompanyDTO = DTOMapper.toCargoCompanyDTO(cargoCompany);
            return ResponseEntity.ok()
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_CARGO_COMPANY_WITH_ID, true, cargoCompanyDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseBuilder.buildResponse(Shop24APIMessages.CARGO_COMPANY_NOT_FOUND, false, null));
        }
    }
    @Tag(name = "get", description = "GET methods of Employee APIs")

    @GetMapping
    public ResponseEntity<Object> getAllCargoCompanies() {
        List<CargoCompany> cargoCompanyList = cargoCompanyService.getAllCargoCompanies();
        List<CargoCompanyDTO> cargoCompanyDTOList = cargoCompanyList.stream().map(DTOMapper::toCargoCompanyDTO).collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIVED_ALL_CARGO_COMPANY, true, cargoCompanyDTOList));
    }
    
    
    
//    @GetMapping("/{companyId}/drinks-transported-by-date-range")
//    public ResponseEntity<Object> getDrinksTransportedByDateRangeAndCargoCompany(
//            @PathVariable Long companyId,
//            @RequestParam("startDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam("endDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//        try {
//            CargoCompany cargoCompany = cargoCompanyService.getCargoCompanyById(companyId);
//            List<Drink> drinks = cargoCompanyService.getDrinksTransportedByDateRangeAndCargoCompany(startDate, endDate, cargoCompany);
//            return ResponseEntity.ok().body(ResponseBuilder.buildResponse(Shop24APIMessages.RETRIEVED_DRINKS_TRANSPORTED, true, drinks));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBuilder.buildResponse(e.getMessage(), false, null));
//        }
//    }
    
   
}