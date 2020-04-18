package com.online.shop.application.mappers;

import com.online.shop.application.dto.CategoryProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    private ProductMapper productMapper = new ProductMapper();

    @Test
    public void toCategoryProductDto() {
        Product product = getProduct();
        CategoryProductDto dto = productMapper.toCategoryProductDto(product);
        assertThat(dto).isEqualToComparingFieldByField(expectedCategoryProductDto());
    }

    @Test
    public void toProductDto() {
        Product product = getProduct();
        assertThat(productMapper.toProductDto(product))
                .isEqualToComparingFieldByField(expectedProductDto());
    }

    @Test
    public void updateEntity() {
        Product product = getProduct();
        ProductDto productDto = expectedProductDto();
        productDto.setName("newName");
        productMapper.updateEntity(product, productDto);
        assertThat(product.getName()).isEqualTo("newName");
    }

    @Test
    public void toEntity() {
        ProductDto dto = expectedProductDto();
        Product product = productMapper.toEntity(dto);
        assertThat(product).isEqualToComparingFieldByField(getProduct());
    }

    private ProductDto expectedProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(-1L);
        dto.setCategory(Category.AUTOMOBILE);
        dto.setDescription("desc");
        dto.setName("n");
        dto.setProperties(Collections.singletonList("prop"));
        dto.setPrice(BigDecimal.TEN);
        return dto;
    }

    private CategoryProductDto expectedCategoryProductDto() {
        CategoryProductDto categoryProductDto = new CategoryProductDto();
        categoryProductDto.setId(-1L);
        categoryProductDto.setName("n");
        categoryProductDto.setDescription("desc");
        categoryProductDto.setPrice(BigDecimal.TEN);
        return categoryProductDto;
    }

    private Product getProduct() {
        return Product.builder()
                .id(-1L)
                .category(Category.AUTOMOBILE)
                .description("desc")
                .price(BigDecimal.TEN)
                .properties(Collections.singletonList("prop"))
                .name("n")
                .purchases(new ArrayList<>())
                .reviews(new ArrayList<>())
                .build();
    }

}