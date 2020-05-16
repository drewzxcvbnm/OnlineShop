package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class PartialProductDto {
    private Long id;
    @Size(min = 1, max = 255)
    private String name;
    @NotEmpty
    private String description;
    @Positive
    @NotNull
    private BigDecimal price;
}
