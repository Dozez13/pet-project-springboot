package com.example.task16.db.repository;

import com.example.task16.db.entity.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CarCategoryRepository extends JpaRepository<CarCategory, UUID> {
    @Query("SELECT cc from CarCategory cc where cc.carCategoryName in (SELECT distinct c.carCategory.carCategoryName from Car c)")
    List<CarCategory> findByIdInCars_carCategory_Id();
    CarCategory findByCarCategoryName(String carCategoryName);
}
