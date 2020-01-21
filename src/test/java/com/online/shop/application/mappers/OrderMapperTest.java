package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    private OrderMapper orderMapper = new OrderMapper();

    @Test
    public void toEntity() {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress("ad");
        orderDto.setBankAccount("bc");
        orderDto.setCustomerName("cn");
        orderDto.setCustomerSurname("cs");
        assertThat(orderMapper.toEntity(orderDto))
                .isEqualToComparingFieldByField(expected());
    }

    private Order expected() {
        return Order.builder()
                .address("ad")
                .bankAccount("bc")
                .customerName("cn")
                .customerSurname("cs")
                .purchases(new ArrayList<>())
                .build();
    }
}