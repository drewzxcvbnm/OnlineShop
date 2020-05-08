package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class PartialProductDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @Positive
    @NotNull
    private BigDecimal price;
}
