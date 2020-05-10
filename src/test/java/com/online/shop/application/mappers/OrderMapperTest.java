package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.UserInfo;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    public void toEntity() {
        OrderDto orderDto = getOrderDto();
        assertThat(orderMapper.toOrder(orderDto))
                .isEqualToComparingFieldByField(expected());
    }

    @Test
    public void updateOrderDto() {
        OrderDto orderDto = new OrderDto();
        UserInfo info = getUserInfo();
        orderMapper.updateOrderDto(orderDto, info);
        assertThat(orderDto).isEqualToComparingFieldByField(getOrderDto());
    }

    private UserInfo getUserInfo() {
        UserInfo info = new UserInfo();
        info.setAddress("ad");
        info.setBankAccount("bc");
        info.setName("cn");
        info.setSurname("cs");
        return info;
    }

    private OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress("ad");
        orderDto.setBankAccount("bc");
        orderDto.setName("cn");
        orderDto.setSurname("cs");
        return orderDto;
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