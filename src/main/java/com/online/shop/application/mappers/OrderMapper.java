package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setAddress(dto.getAddress());
        order.setBankAccount(dto.getBankAccount());
        order.setCustomerName(dto.getCustomerName());
        order.setCustomerSurname(dto.getCustomerSurname());
        return order;
    }

}
