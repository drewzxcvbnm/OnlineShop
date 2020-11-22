package com.online.shop.application.dto;

import com.online.shop.application.services.validation.UniqueUsername;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.online.shop.application.Constants.IBAN_REGEX;
import static com.online.shop.application.Constants.PHONE_REGEX;

@Data
public class UserDto {
    @NotEmpty
    @UniqueUsername
    private String username;
    @Size(min = 6, max = 255)
    private String password;
    @Size(min = 1, max = 255)
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String name;
    @Size(min = 1, max = 255)
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String surname;
    @Email
    @Size(min = 1, max = 255)
    private String email;
    @Size(min = 1, max = 255)
    private String address;
    @Size(min = 1, max = 255)
    @Pattern(regexp = PHONE_REGEX)
    private String phoneNumber;
    @Size(min = 1, max = 255)
    @Pattern(regexp = IBAN_REGEX)
    private String bankAccount;
    private MultipartFile profilePicture;
}
