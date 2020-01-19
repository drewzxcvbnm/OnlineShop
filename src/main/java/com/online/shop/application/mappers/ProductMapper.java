package com.online.shop.application.mappers;

import com.online.shop.application.dto.LightProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public LightProductDto toCategoryProductDto(Product product) {
        LightProductDto lightProductDto = new LightProductDto();
        lightProductDto.setId(product.getId());
        lightProductDto.setName(product.getName());
        lightProductDto.setDescription(product.getDescription());
        lightProductDto.setPrice(product.getPrice());
        return lightProductDto;
    }

    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setProperties(product.getProperties());
        return productDto;
    }

    public Product updateEntity(Product entity, ProductDto dto) {
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setProperties(dto.getProperties());
        return entity;
    }

}
