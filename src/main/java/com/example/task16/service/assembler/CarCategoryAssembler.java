package com.example.task16.service.assembler;


import com.example.task16.db.entity.Car;
import com.example.task16.db.entity.CarCategory;
import com.example.task16.service.dto.CarCategoryDto;
import com.example.task16.service.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class CarCategoryAssembler implements Assembler<CarCategory, CarCategoryDto> {
    @Override
    public CarCategoryDto mergeAggregateIntoDto(CarCategory entity) {
        CarCategoryDto carCategoryDto = new CarCategoryDto();
        carCategoryDto.setCarCategoryName(entity.getCarCategoryName());
        carCategoryDto.setCostPerOneKilometer(entity.getCostPerOneKilometer());
        carCategoryDto.setCarCategoryImage(entity.getCarCategoryImage());
        carCategoryDto.setDiscountPerPrice(entity.getDiscountPerPrice());
        carCategoryDto.setCars(entity.getCars().stream().map(this::mergeAggregateIntoDto).collect(Collectors.toList()));
        return carCategoryDto;
    }

    @Override
    public CarCategory mergeDtoIntoAggregate(CarCategoryDto dto) {
        CarCategory carCategory = new CarCategory();
        carCategory.setCarCategoryImage(dto.getCarCategoryImage());
        carCategory.setCarCategoryName(dto.getCarCategoryName());
        carCategory.setDiscountPerPrice(dto.getDiscountPerPrice());
        carCategory.setCostPerOneKilometer(dto.getCostPerOneKilometer());
        carCategory.setCars(dto.getCars().stream().map(carDto -> {
            Car car =mergeDtoIntoAggregate(carDto);
            car.setCarCategory(carCategory);
            return car;
        }).collect(Collectors.toList()));
        return carCategory;
    }
    private CarDto mergeAggregateIntoDto(Car car){
        CarDto carDto = new CarDto();
        carDto.setCarCategory(car.getCarCategory().getId());
        carDto.setCarId(car.getCarId());
        carDto.setCarImage(car.getCarImage());
        carDto.setCarState(car.getCarState());
        carDto.setNumOfPas(car.getNumOfPas());
        carDto.setCarName(car.getCarName());
        return carDto;
    }

    private Car mergeDtoIntoAggregate(CarDto dto) {
        Car car = new Car();
        car.setCarId(dto.getCarId());
        car.setCarImage(dto.getCarImage());
        car.setCarName(dto.getCarName());
        car.setCarState(dto.getCarState());
        car.setNumOfPas(dto.getNumOfPas());
        return car;
    }
}

