package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.services.product.ProductDeletionService;
import com.online.shop.application.services.product.ProductPersistenceService;
import com.online.shop.application.services.product.ProductRetrievalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.online.shop.application.TestBaseUtils.COMPUTERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @Mock
    private ProductRetrievalService productRetrievalService;
    @Mock
    private ProductDeletionService productDeletionService;
    @Mock
    private ProductPersistenceService productPersistenceService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    @InjectMocks
    private AdminController adminController;

    @Test
    public void saveProduct() {
        ProductDto dto = new ProductDto();
        when(bindingResult.hasErrors()).thenReturn(false).thenReturn(true);
        assertThat(adminController.updateProduct(-1L, dto, bindingResult, model))
                .isEqualTo("redirect:/product/-1");
        assertThat(adminController.updateProduct(-1L, dto, bindingResult, model))
                .isEqualTo("edit-product");
        verify(productPersistenceService).updateProduct(-1L, dto);
        verify(model).addAttribute(eq("product"), any());
    }

    @Test
    public void testSaveProduct() {
        ProductDto product = new ProductDto();
        product.setId(-1L);
        when(bindingResult.hasErrors()).thenReturn(true).thenReturn(false);
        when(productPersistenceService.createProduct(any())).thenReturn(product);
        assertThat(adminController.saveProduct(new ProductDto(), bindingResult, model))
                .isEqualTo("edit-product");
        assertThat(adminController.saveProduct(new ProductDto(), bindingResult, model))
                .isEqualTo("redirect:/product/-1");
        verify(model).addAttribute(eq("product"), any());
        verify(productPersistenceService).createProduct(any());

    }

    @Test
    public void editProduct() {
        assertThat(adminController.editProduct(-1L, model))
                .isEqualTo("edit-product");
        verify(model).addAttribute(eq("product"), any());
        verify(productRetrievalService).getProduct(-1L);
    }

    @Test
    public void deleteProduct() {
        assertThat(adminController.deleteProduct(-1L, COMPUTERS.getId(), model))
                .isEqualTo("redirect:/category/" + COMPUTERS.getId());
        verify(productDeletionService).deleteProduct(-1L);
    }

    @Test
    public void createProduct() {
        assertThat(adminController.createProduct(model))
                .isEqualTo("edit-product");
        verify(model).addAttribute(eq("product"), any());
    }

}