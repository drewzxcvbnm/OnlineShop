package com.online.shop.application.services;

import com.online.shop.application.dto.PartialProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.product.ProductRetrievalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductRetrievalServiceTest {
    @Mock
    private ProductRepo productRepo;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductRetrievalService retrievalService;


    @Test
    public void getProductsByCategory() {
        when(productMapper.toPartialProductDto(any())).thenReturn(new PartialProductDto());
        Category category = new Category();
        category.getProducts().add(new Product());
        assertThat(retrievalService.getProductsByCategory(category).size())
                .isEqualTo(1);
    }

    @Test(expected = ItemNotFoundException.class)
    public void getProduct() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(new Product())).thenReturn(Optional.empty());
        retrievalService.getProduct(1L);
        verify(productMapper).toProductDto(any());
        retrievalService.getProduct(1L);
    }
}