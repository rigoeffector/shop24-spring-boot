package com.shop24.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cargo")

public class CargoCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private Long id;

    private String name;
  
    private String address;
  
    private double latitude;
  
    private double longitude;

}