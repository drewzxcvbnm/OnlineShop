package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class OrderDto {
    @Pattern(regexp = "[A-Z][a-z]+")
    String customerName;
    @Pattern(regexp = "[A-Z][a-z]+")
    String customerSurname;
    @NotEmpty
    String address;
    @NotEmpty
    String bankAccount;
}
