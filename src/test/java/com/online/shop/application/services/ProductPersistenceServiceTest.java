package com.online.shop.application.services;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.ProductRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

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
    @InjectMocks
    private ProductPersistenceService productPersistenceService;

    @Test
    public void updateProduct() {
        Product product = new Product();
        ProductDto dto = new ProductDto();
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        productPersistenceService.updateProduct(1L, dto);
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