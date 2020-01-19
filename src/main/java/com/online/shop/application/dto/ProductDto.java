package com.online.shop.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private List<String> properties;
    private BigDecimal price;
}
