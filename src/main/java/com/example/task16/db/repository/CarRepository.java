package com.example.task16.db.repository;

import com.example.task16.db.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Nullable
    Car findByNumOfPasAndCarCategory_CarCategoryName(Integer numOfPas,String carCategoryName);
}
