package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class OrderDto {
    @Pattern(regexp = "^[A-Z][a-z]+")
    String customerName;
    @Pattern(regexp = "^[A-Z][a-z]+")
    String customerSurname;
    @NotEmpty
    String address;
    @NotEmpty
    @Pattern(regexp = "^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$")
    String bankAccount;
}
