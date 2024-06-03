package com.shop24.dto;

public class DrinkConsumptionDTO {
    private String name;
    private long totalQuantity;

    public DrinkConsumptionDTO(String name, long totalQuantity) {
        this.name = name;
        this.totalQuantity = totalQuantity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
