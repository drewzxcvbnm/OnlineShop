package com.online.shop.application.dto;

import lombok.Data;

@Data
public class OrderDto {
    String customerName;
    String customerSurname;
    String address;
    String bankAccount;
}
