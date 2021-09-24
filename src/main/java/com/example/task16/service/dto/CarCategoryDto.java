package com.example.task16.service.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarCategoryDto {
    private UUID id ;

    private String carCategoryName;

    private Double costPerOneKilometer;

    private Double discountPerPrice;

    private byte[] carCategoryImage;

    private List<CarDto> cars = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCarCategoryName() {
        return carCategoryName;
    }

    public void setCarCategoryName(String carCategoryName) {
        this.carCategoryName = carCategoryName;
    }

    public Double getCostPerOneKilometer() {
        return costPerOneKilometer;
    }

    public void setCostPerOneKilometer(Double costPerOneKilometer) {
        this.costPerOneKilometer = costPerOneKilometer;
    }

    public Double getDiscountPerPrice() {
        return discountPerPrice;
    }

    public void setDiscountPerPrice(Double discountPerPrice) {
        this.discountPerPrice = discountPerPrice;
    }

    public byte[] getCarCategoryImage() {
        return carCategoryImage;
    }

    public void setCarCategoryImage(byte[] carCategoryImage) {
        this.carCategoryImage = carCategoryImage;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}
