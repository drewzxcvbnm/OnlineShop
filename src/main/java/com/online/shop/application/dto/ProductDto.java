package com.online.shop.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends PartialProductDto {
    private CategoryDto category;
    private List<@Size(min = 1, max = 255) String> properties = new ArrayList<>();
    private List<ReviewDto> reviews = new ArrayList<>();
}
