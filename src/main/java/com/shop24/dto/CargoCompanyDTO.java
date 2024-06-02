package com.shop24.dto;

public class CargoCompanyDTO {
    private Long cargoCompanyId;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public Long getCargoCompanyId() {
        return cargoCompanyId;
    }

    public void setCargoCompanyId(Long cargoCompanyId) {
        this.cargoCompanyId = cargoCompanyId;
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
}
