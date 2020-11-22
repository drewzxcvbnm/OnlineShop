package com.online.shop.application.services;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.User;
import com.online.shop.application.mappers.OrderMapper;
import com.online.shop.application.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    public List<OrderDto> getUsersOrders(User user) {
        return user.getOrders().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private OrderDto toDto(Order order) {
        OrderDto orderDto = orderMapper.toOrderDto(order);
        orderDto.getPurchasedProducts().addAll(productMapper.toProductDtos(order.getPurchasedProducts()));
        return orderDto;
    }

}
