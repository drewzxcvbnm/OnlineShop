package com.online.shop.application.dto;

import com.online.shop.application.services.validation.UniqueUsername;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.online.shop.application.Constants.IBAN_REGEX;
import static com.online.shop.application.Constants.PHONE_REGEX;

@Data
public class UserDto {
    @NotEmpty
    @UniqueUsername
    private String username;
    @Length(min = 6)
    @NotEmpty
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]+")
    private String surname;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String address;
    @NotEmpty
    @Pattern(regexp = PHONE_REGEX)
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = IBAN_REGEX)
    private String bankAccount;
}
