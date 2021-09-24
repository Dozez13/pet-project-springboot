package com.example.task16.web.converter;

import com.example.task16.service.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOrderDto implements Converter<String, OrderDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public OrderDto convert(String s) {
        return objectMapper.readValue(s,OrderDto.class);
    }
}
