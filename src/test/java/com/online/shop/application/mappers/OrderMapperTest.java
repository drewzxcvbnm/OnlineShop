package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    public void toEntity() {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress("ad");
        orderDto.setBankAccount("bc");
        orderDto.setName("cn");
        orderDto.setSurname("cs");
        assertThat(orderMapper.toOrder(orderDto))
                .isEqualToComparingFieldByField(expected());
    }

    private Order expected() {
        return Order.builder()
                .address("ad")
                .bankAccount("bc")
                .name("cn")
                .surname("cs")
                .purchases(new ArrayList<>())
                .build();
    }
}