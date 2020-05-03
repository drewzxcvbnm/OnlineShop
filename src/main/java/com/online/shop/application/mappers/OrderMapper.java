package com.online.shop.application.mappers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    Order toOrder(OrderDto dto);

}
