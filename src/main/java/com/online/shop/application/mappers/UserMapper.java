package com.online.shop.application.mappers;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.entities.User;
import com.online.shop.application.entities.UserInfo;
import com.online.shop.application.mappers.service.UserImageMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = UserImageMapper.class)
public interface UserMapper {

    @Mapping(target = "userInfo", source = "dto")
    @Mapping(target = "productReviews", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authority", ignore = true)
    @Mapping(target = "profilePicture", source = "dto", qualifiedByName = "userImage")
    User toUser(UserDto dto);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "name", source = "user.userInfo.name")
    @Mapping(target = "surname", source = "user.userInfo.surname")
    @Mapping(target = "email", source = "user.userInfo.email")
    @Mapping(target = "bankAccount", source = "user.userInfo.bankAccount")
    @Mapping(target = "phoneNumber", source = "user.userInfo.phoneNumber")
    @Mapping(target = "address", source = "user.userInfo.address")
    @BeanMapping(ignoreByDefault = true)
    UserDto toDto(User user);

    @Mapping(source = "name", target = "user.userInfo.name")
    @Mapping(source = "surname", target = "user.userInfo.surname")
    @Mapping(source = "email", target = "user.userInfo.email")
    @Mapping(source = "bankAccount", target = "user.userInfo.bankAccount")
    @Mapping(source = "phoneNumber", target = "user.userInfo.phoneNumber")
    @Mapping(source = "address", target = "user.userInfo.address")
    @BeanMapping(ignoreByDefault = true)
    void updateUser(@MappingTarget User user, UserDto userDto);

    @Mapping(target = "id", ignore = true)
    UserInfo toUserInfo(UserDto dto);

}
