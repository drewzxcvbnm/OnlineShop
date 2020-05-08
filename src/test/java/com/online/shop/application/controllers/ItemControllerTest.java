package com.online.shop.application.controllers;

import com.online.shop.application.entities.Category;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.services.product.ProductRetrievalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static com.online.shop.application.TestBaseUtils.COMPUTERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

    @Mock
    private Model model;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private ProductRetrievalService productRetrievalService;
    @InjectMocks
    private ItemController itemController;

    @Test
    public void categoryProducts() {
        Category c = new Category();
        when(categoryRepo.getById(COMPUTERS.getId())).thenReturn(c);
        assertThat(itemController.categoryProducts(COMPUTERS.getId(), model))
                .isEqualTo("category-products");
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    public void getProduct() {
        assertThat(itemController.getProduct(-1L, model))
                .isEqualTo("product-page");
        verify(model).addAttribute(eq("product"), any());
    }

}