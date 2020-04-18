package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.ProductDeletionService;
import com.online.shop.application.services.ProductPersistenceService;
import com.online.shop.application.services.ProductRetrievalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @Mock
    private ProductRetrievalService productRetrievalService;
    @Mock
    private ProductPersistenceService productPersistenceService;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private ProductDeletionService productDeletionService;
    @Mock
    private Model model;
    @InjectMocks
    private ItemController itemController;

    @Test
    public void categoryProducts() {
        assertThat(itemController.categoryProducts(Category.COMPUTERS, model))
                .isEqualTo("category-products");
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    public void getProduct() {
        assertThat(itemController.getProduct(-1L, model))
                .isEqualTo("product-page");
        verify(model).addAttribute(eq("product"), any());
    }

    @Test
    public void saveProduct() {
        ProductDto dto = new ProductDto();
        assertThat(itemController.saveProduct(-1L, dto, model))
                .isEqualTo("product-page");
        verify(productPersistenceService).updateProduct(-1L, dto);
        verify(model).addAttribute(eq("product"), any());
    }

    @Test
    public void testSaveProduct() {
        ProductDto product = new ProductDto();
        product.setId(-1L);
        when(productPersistenceService.createProduct(any())).thenReturn(product);
        assertThat(itemController.saveProduct(new ProductDto(), model))
                .isEqualTo("redirect:/product/-1");
        verify(model).addAttribute(eq("product"), any());
        verify(productPersistenceService).createProduct(any());

    }

    @Test
    public void editProduct() {
        assertThat(itemController.editProduct(-1L, model))
                .isEqualTo("edit-product");
        verify(model).addAttribute(eq("product"), any());
        verify(productRetrievalService).getProduct(-1L);
    }

    @Test
    public void deleteProduct() {
        assertThat(itemController.deleteProduct(-1L, Category.COMPUTERS, model))
                .isEqualTo("redirect:/category/COMPUTERS");
        verify(productDeletionService).deleteProduct(-1L);
    }

    @Test
    public void createProduct() {
        assertThat(itemController.createProduct(model))
                .isEqualTo("edit-product");
        verify(model).addAttribute(eq("product"), any());
    }
}