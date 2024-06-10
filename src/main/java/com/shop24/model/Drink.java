package com.shop24.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity

@Table( name = "drinks")

public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name =  "name")
  
    private String name;
    @Column(name = "description")

    private String description;
    @Column(name = "price")
   
    private double price;
    @Column(name = "quantity")
  
    private int quantity;
    
//    @ManyToOne
//    @JoinColumn(name = "receipt_id")
//    private Receipt receipt;
    
	public Drink() {
		
		
	}
	public Drink(Long id, String name, String description, double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public Receipt getReceipt() {
//	        return receipt;
//	 }
//
//	public void setReceipt(Receipt receipt) {
//	        this.receipt = receipt;
//	 }
	
  
}
