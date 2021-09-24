package com.example.task16.service;

import com.example.task16.service.dto.OrderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Long orderCount();
    Long countUserOrder(UUID userId);
    List<OrderDto> findOrdersByUser(UUID userId, Pageable pageable);
    List<OrderDto> findFilSortOrders(OrderDto orderDto, Pageable pageable);
    String makeOrder(String[] stingNumbers, String[] categories, String userAddress, String userDestination, String login);


}
