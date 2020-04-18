package com.online.shop.application.mappers;

import com.online.shop.application.dto.CategoryProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public CategoryProductDto toCategoryProductDto(Product product) {
        CategoryProductDto categoryProductDto = new CategoryProductDto();
        categoryProductDto.setId(product.getId());
        categoryProductDto.setName(product.getName());
        categoryProductDto.setDescription(product.getDescription());
        categoryProductDto.setPrice(product.getPrice());
        return categoryProductDto;
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

    public Product toEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setProperties(dto.getProperties());
        return entity;
    }


}
