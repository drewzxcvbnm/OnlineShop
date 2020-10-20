package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

import static com.online.shop.application.Constants.IBAN_REGEX;

@Data
public class OrderDto {
    private Long id;
    @Pattern(regexp = "^[A-Z][a-z]+")
    String name;
    @Pattern(regexp = "^[A-Z][a-z]+")
    String surname;
    @NotEmpty
    String address;
    @NotEmpty
    @Pattern(regexp = IBAN_REGEX)
    String bankAccount;
    List<ProductDto> purchasedProducts = new ArrayList<>();
}
