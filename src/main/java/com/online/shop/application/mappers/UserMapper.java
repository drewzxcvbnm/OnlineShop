package com.online.shop.application.mappers;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.entities.User;
import com.online.shop.application.entities.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "userInfo", source = "dto")
    @Mapping(target = "productReviews", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authority", ignore = true)
    User toUser(UserDto dto);

    @Mapping(target = "id", ignore = true)
    UserInfo toUserInfo(UserDto dto);

}
