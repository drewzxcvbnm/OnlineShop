package com.online.shop.application.mappers;

import com.online.shop.application.TestBaseUtils;
import com.online.shop.application.dto.PartialProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void toCategoryProductDto() {
        Product product = getProduct();
        PartialProductDto dto = productMapper.toPartialProductDto(product);
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
        assertThat(product)
                .usingRecursiveComparison()
                .ignoringFields("category")
                .isEqualTo(getProduct());
    }

    private ProductDto expectedProductDto() {
        ProductDto dto = new ProductDto();
        dto.setId(-1L);
        dto.setDescription("desc");
        dto.setName("n");
        dto.setProperties(new ArrayList<>(Collections.singletonList("prop")));
        dto.setPrice(BigDecimal.TEN);
        return dto;
    }

    private PartialProductDto expectedCategoryProductDto() {
        PartialProductDto partialProductDto = new PartialProductDto();
        partialProductDto.setId(-1L);
        partialProductDto.setName("n");
        partialProductDto.setDescription("desc");
        partialProductDto.setPrice(BigDecimal.TEN);
        return partialProductDto;
    }

    private Product getProduct() {
        return Product.builder()
                .id(-1L)
                .category(TestBaseUtils.AUTOMOBILE)
                .description("desc")
                .price(BigDecimal.TEN)
                .properties(new ArrayList<>(Collections.singletonList("prop")))
                .name("n")
                .purchases(new ArrayList<>())
                .reviews(new ArrayList<>())
                .build();
    }

}