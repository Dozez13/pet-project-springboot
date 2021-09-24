package com.example.task16.service.dto;



import com.example.task16.db.entity.CarState;

import java.util.UUID;

public class CarDto {
    private UUID carId ;

    private Integer numOfPas;


    private CarState carState;

    private String carName;

    private byte[] carImage;

    private UUID carCategory;

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public Integer getNumOfPas() {
        return numOfPas;
    }

    public void setNumOfPas(Integer numOfPas) {
        this.numOfPas = numOfPas;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public byte[] getCarImage() {
        return carImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    public UUID getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(UUID carCategory) {
        this.carCategory = carCategory;
    }
}
