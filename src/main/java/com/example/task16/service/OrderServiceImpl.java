package com.example.task16.service;

import com.example.task16.db.entity.*;
import com.example.task16.db.repository.*;
import com.example.task16.service.assembler.Assembler;
import com.example.task16.service.dto.OrderDto;
import com.example.task16.service.util.DistanceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService{
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository repository;
    private final Assembler<Order,OrderDto> orderDtoAssembler;
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public OrderServiceImpl(OrderRepository repository, Assembler<Order, OrderDto> orderDtoAssembler, CarRepository carRepository, CarCategoryRepository carCategoryRepository, UserRepository userRepository, ProfileRepository profileRepository) {
        this.repository = repository;
        this.orderDtoAssembler = orderDtoAssembler;
        this.carRepository = carRepository;
        this.carCategoryRepository = carCategoryRepository;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Long orderCount() {
        return repository.count();
    }

    @Override
    public Long countUserOrder(UUID userId) {
        return repository.countByUser_UserId(userId);
    }

    @Override
    public List<OrderDto> findOrdersByUser(UUID userId, Pageable pageable) {
        return repository.findByUser_UserId(userId, pageable).stream().map(orderDtoAssembler::mergeAggregateIntoDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findFilSortOrders(OrderDto orderDto, Pageable pageable) {
        return repository.findAll(Example.of(orderDtoAssembler.mergeDtoIntoAggregate(orderDto)),pageable)
                .stream()
                .map(orderDtoAssembler::mergeAggregateIntoDto)
                .collect(Collectors.toList());
    }

    @Override
    public String makeOrder(String[] stingNumbers, String[] categories, String userAddress, String userDestination, String login) {
        String message = null;
        int[] numbers = Stream.of(stingNumbers).mapToInt(Integer::parseInt).toArray();
        Car[] foundCars = new Car[categories.length];
        User foundUser = userRepository.findByLogin(login);
        for (int i = 0; i < foundCars.length; i++) {
            foundCars[i] = carRepository.findByNumOfPasAndCarCategory_CarCategoryName(numbers[i], categories[i]);
            if (foundCars[i] == null) {
                LOGGER.info("Car with  {} number of passenger and category {} Is not found", numbers[i], categories[i]);
             //   throw new ApplicationEXContainer.ApplicationSendOrderMessageException(String.format("%s %d", categories[i], numbers[i]));
            }
            foundCars[i].setCarState(CarState.ON_ORDER);
            carRepository.save(foundCars[i]);
            double distance = DistanceUtil.getDistance(userAddress, userDestination);
            CarCategory foundCarCategory = carCategoryRepository.findByCarCategoryName(categories[i]);
            double discount = foundCarCategory.getDiscountPerPrice();
            double costPerKilo = foundCarCategory.getCostPerOneKilometer();
            int scale = (int) Math.pow(10, 1);
            double orderCost = (double) Math.round(((distance * costPerKilo) - ((distance * costPerKilo) * discount)) * scale) / scale;
            Order order = new Order();
            order.setUser(foundUser);
            order.setCar(foundCars[i]);
            order.setOrderDate(LocalDateTime.now());
            order.setUserAddress(userAddress);
            order.setUserDestination(userDestination);
            order.setOrderCost(orderCost);
            Profile profile = foundUser.getProfile();
            profile.setAccountBalance(profile.getAccountBalance()+orderCost);
            profileRepository.save(profile);
            foundUser.getOrders().add(order);
            userRepository.save(foundUser);
            if (message == null) {
                message = DistanceUtil.takenTime(distance);
            }
        }
        return message;
    }
}
