package com.example.task16.service.assembler;


import com.example.task16.db.entity.Order;
import com.example.task16.service.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderAssembler implements Assembler<Order, OrderDto>{

    @Override
    public OrderDto mergeAggregateIntoDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(entity.getOrderId());
        orderDto.setOrderDate(entity.getOrderDate());
        orderDto.setOrderCost(entity.getOrderCost());
        orderDto.setUserAddress(entity.getUserAddress());
        orderDto.setUserDestination(entity.getUserDestination());
        orderDto.setCarId(entity.getCar().getCarId());
        orderDto.setUserId(entity.getUser().getUserId());
        return orderDto;
    }

    @Override
    public Order mergeDtoIntoAggregate(OrderDto dto) {
       Order order = new Order();
       order.setOrderId(dto.getOrderId());
       order.setOrderCost(dto.getOrderCost());
       order.setOrderDate(dto.getOrderDate());
       order.setUserAddress(dto.getUserAddress());
       order.setUserDestination(dto.getUserDestination());
      return order;
    }
}
