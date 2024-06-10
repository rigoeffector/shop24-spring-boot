package com.shop24.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cargo_company")

public class CargoCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private Long id;
    @Column(name = "name")

    private String name;
    
    @Column(name = "address")
    private String address;
  
    @Column(name = "latitude")
   
    private double latitude;
    
    @Column(name = "longitude")
    private double longitude;
    
//    remove this 
    
    @Column(name = "distance") // Add distance attribute
    private Double distance; 
    
    public CargoCompany() {
		
		
	}

    
    public CargoCompany(Long id, String name, String address, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

    
  
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}


}