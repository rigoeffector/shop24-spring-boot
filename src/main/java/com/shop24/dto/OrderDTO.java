package com.shop24.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private ClientDTO client;
    private List<DrinkDTO> drinks;
    private boolean isPaid;
    private boolean isCompleted;
    private CargoCompanyDTO cargoCompany; // Add cargo company field

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<DrinkDTO> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkDTO> drinks) {
        this.drinks = drinks;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    
    private LocalDateTime createdAt;


    public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	private LocalDateTime updatedAt;

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public CargoCompanyDTO getCargoCompany() {
        return cargoCompany;
    }

    public void setCargoCompany(CargoCompanyDTO cargoCompany) {
        this.cargoCompany = cargoCompany;
    }
}
