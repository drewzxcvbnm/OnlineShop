package com.online.shop.application.mappers;

import com.online.shop.application.dto.PartialProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {ReviewMapper.class, CategoryMapper.class})
public interface ProductMapper {

    PartialProductDto toPartialProductDto(Product product);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> product);

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntity(@MappingTarget Product entity, ProductDto dto);

    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto dto);

}
