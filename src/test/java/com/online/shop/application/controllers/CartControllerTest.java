package com.online.shop.application.controllers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.services.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private CartController cartController;

    @Test
    public void getCartContents() {
        assertThat(cartController.getCartContents(model))
                .isEqualTo("cart");
        verify(model).addAttribute(eq("products"), any());
        verify(cartService).getCartProducts();
    }

    @Test
    public void addToCart() {
        cartController.addToCart(10L);
        verify(cartService).addProductToCart(10L);
    }

    @Test
    public void getCartSize() {
        when(cartService.getCartSize()).thenReturn(10L);
        assertThat(cartController.getCartSize().getBody()).isEqualTo(10L);
    }

    @Test
    public void clearCart() {
        cartController.clearCart(model);
        verify(cartService).clearCart();
    }

    @Test
    public void checkout() {
        assertThat(cartController.checkout(new OrderDto())).isEqualTo("form-order");
    }

    @Test
    public void submitOrderInvalid() {
        OrderDto orderDto = new OrderDto();
        when(bindingResult.hasErrors()).thenReturn(true);
        assertThat(cartController.submitOrder(orderDto, bindingResult, model)).isEqualTo("form-order");
        verify(bindingResult).hasErrors();
    }

    @Test
    public void submitOrderValid() {
        OrderDto orderDto = new OrderDto();
        when(bindingResult.hasErrors()).thenReturn(false);
        assertThat(cartController.submitOrder(orderDto, bindingResult, model)).isEqualTo("index");
        verify(cartService).submitOrder(orderDto);
        verify(model).addAttribute(eq("showMessage"), any());
        verify(model).addAttribute(eq("message"), any());
        verify(bindingResult).hasErrors();
    }
}