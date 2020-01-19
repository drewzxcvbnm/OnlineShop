package com.online.shop.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LightProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
