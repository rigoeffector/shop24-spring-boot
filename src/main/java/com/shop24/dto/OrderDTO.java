package com.shop24.dto;

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
