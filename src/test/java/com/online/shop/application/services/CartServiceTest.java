package com.online.shop.application.services;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.dto.PartialProductDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.Product;
import com.online.shop.application.mappers.OrderMapper;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.OrderRepo;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.user.UserService;
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
    @Mock
    private UserService userService;
    @InjectMocks
    private CartService cartService;

    @Test
    public void addProductToCart() {
        when(productRepo.findObligatoryProduct(anyLong())).thenReturn(new Product());
        cartService.addProductToCart(-1L);
        verify(productRepo).findObligatoryProduct(-1L);
        assertThat(cartService.getCartSize()).isEqualTo(1);
    }

    @Test
    public void getCartProducts() {
        when(productRepo.findObligatoryProduct(anyLong())).thenReturn(new Product());
        when(productMapper.toPartialProductDto(any())).thenReturn(new PartialProductDto());
        cartService.addProductToCart(-1L);
        verify(productRepo).findObligatoryProduct(-1L);
        assertThat(cartService.getCartProducts().size()).isEqualTo(1);
    }

    @Test
    public void submitOrder() {
        when(userService.getCurrentUser()).thenReturn(Optional.empty());
        when(productRepo.findObligatoryProduct(anyLong())).thenReturn(Product.builder().id(1L).build());
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(Product.builder().id(1L).build()));
        cartService.addProductToCart(-1L);

        OrderDto dto = new OrderDto();
        Order order = new Order();
        when(orderMapper.toOrder(dto)).thenReturn(order);
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(new Product()));
        cartService.submitOrder(dto);
        verify(orderRepo).save(order);
    }

    @Test
    public void clearCart() {
        when(productRepo.findObligatoryProduct(anyLong())).thenReturn(new Product());
        cartService.addProductToCart(-1L);
        verify(productRepo).findObligatoryProduct(-1L);
        assertThat(cartService.getCartSize()).isEqualTo(1);
        cartService.clearCart();
        assertThat(cartService.getCartSize()).isEqualTo(0);
    }
}