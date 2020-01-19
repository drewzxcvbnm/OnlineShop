package com.online.shop.application.services;

import com.online.shop.application.dto.LightProductDto;
import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class CartService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private List<Product> cart = new ArrayList<>();

    public void addProductToCart(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(ItemNotFoundException.supplier("Cannot find item by Id:[%d]", productId));
        cart.add(product);
    }

    public List<LightProductDto> getCartProducts() {
        return cart.stream()
                .map(productMapper::toCategoryProductDto)
                .collect(Collectors.toList());
    }

    public Long getCartSize() {
        return Long.valueOf(cart.size());
    }

    public void clearCart() {
        cart.clear();
    }
}
