package com.example.task16.service;

import com.example.task16.db.entity.CarCategory;
import com.example.task16.db.repository.CarCategoryRepository;
import com.example.task16.service.assembler.Assembler;
import com.example.task16.service.dto.CarCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarCategoryServiceImpl implements CarCategoryService {
    private CarCategoryRepository repository;
    private Assembler<CarCategory, CarCategoryDto> carCategoryDtoAssembler;

    public CarCategoryServiceImpl(CarCategoryRepository repository, Assembler<CarCategory, CarCategoryDto> carCategoryDtoAssembler) {
        this.repository = repository;
        this.carCategoryDtoAssembler = carCategoryDtoAssembler;
    }

    @Override
    public List<CarCategoryDto> findExistingCarC() {
        return repository.findByIdInCars_carCategory_Id()
                .stream()
                .map(carCategoryDtoAssembler::mergeAggregateIntoDto)
                .collect(Collectors.toList());
    }
}
