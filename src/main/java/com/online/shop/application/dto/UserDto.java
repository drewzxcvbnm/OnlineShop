package com.online.shop.application.dto;

import com.online.shop.application.services.validation.UniqueUsername;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
    private String phoneNumber;
    @NotEmpty
    @Pattern(regexp = "^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$")
    private String bankAccount;
}
