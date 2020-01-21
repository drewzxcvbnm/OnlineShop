package com.online.shop.application.services;

import com.online.shop.application.dto.CategoryProductDto;
import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.Product;
import com.online.shop.application.mappers.OrderMapper;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.OrderRepo;
import com.online.shop.application.repositories.ProductRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private ProductRepo productRepo;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderRepo orderRepo;
    @InjectMocks
    private CartService cartService;

    @Test
    public void addProductToCart() {
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(new Product()));
        cartService.addProductToCart(-1L);
        verify(productRepo).findById(-1L);
        assertThat(cartService.getCartSize()).isEqualTo(1);
    }

    @Test
    public void getCartProducts() {
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(new Product()));
        when(productMapper.toCategoryProductDto(any())).thenReturn(new CategoryProductDto());
        cartService.addProductToCart(-1L);
        verify(productRepo).findById(-1L);
        assertThat(cartService.getCartProducts().size()).isEqualTo(1);
    }

    @Test
    public void submitOrder() {
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(Product.builder().id(1L).build()));
        cartService.addProductToCart(-1L);

        OrderDto dto = new OrderDto();
        Order order = new Order();
        when(orderMapper.toEntity(dto)).thenReturn(order);
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(new Product()));
        cartService.submitOrder(dto);
        verify(orderRepo).save(order);
    }

    @Test
    public void clearCart() {
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(new Product()));
        cartService.addProductToCart(-1L);
        verify(productRepo).findById(-1L);
        assertThat(cartService.getCartSize()).isEqualTo(1);
        cartService.clearCart();
        assertThat(cartService.getCartSize()).isEqualTo(0);
    }
}