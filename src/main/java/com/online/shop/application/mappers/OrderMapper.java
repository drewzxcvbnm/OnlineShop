package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    Order toOrder(OrderDto dto);

    @Mapping(target = "purchasedProducts", ignore = true)
    OrderDto toOrderDto(Order order);

    @Mapping(target = "purchasedProducts", ignore = true)
    void updateOrderDto(@MappingTarget OrderDto orderDto, UserInfo userInfo);

}
