package com.online.shop.application.services;

import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.product.ProductDeletionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDeletionServiceTest {

    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ProductDeletionService productDeletionService;

    @Test
    public void deleteProduct() {
        doThrow(new RuntimeException()).when(productRepo).deleteById(eq(-2L));
        productDeletionService.deleteProduct(-1L);
        verify(productRepo).deleteById(-1L);
        productDeletionService.deleteProduct(-2L);
    }
}