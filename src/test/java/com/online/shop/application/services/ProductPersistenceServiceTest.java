package com.online.shop.application.services;

import com.online.shop.application.dto.CategoryDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.product.ProductPersistenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductPersistenceServiceTest {

    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private ProductPersistenceService productPersistenceService;

    @Test
    public void updateProduct() {
        Product product = new Product();
        ProductDto dto = new ProductDto();
        CategoryDto categoryDto = new CategoryDto();
        Category category = new Category();
        categoryDto.setId(23L);
        dto.setCategory(categoryDto);
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepo.getById(23L)).thenReturn(category);
        productPersistenceService.updateProduct(1L, dto);
        assertThat(product.getCategory()).isEqualTo(category);
        verify(productMapper).updateEntity(eq(product), any());
        verify(productRepo).save(any());
    }

    @Test
    public void createProduct() {
        ProductDto dto = new ProductDto();
        Product product = new Product();
        when(productMapper.toEntity(dto)).thenReturn(product);
        productPersistenceService.createProduct(dto);
        verify(productRepo).save(product);
    }
}