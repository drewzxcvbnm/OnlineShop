package com.online.shop.application.mappers.service;

import com.online.shop.application.dto.UserDto;
import lombok.SneakyThrows;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
public class UserImageMapper {

    @Named("userImage")
    public byte[] getProfilePicture(UserDto userDto) {
        return Optional.ofNullable(userDto.getProfilePicture())
                .map(this::toByteArray)
                .orElse(null);
    }

    @SneakyThrows
    private byte[] toByteArray(MultipartFile file) {
        return file.getBytes();
    }

}
