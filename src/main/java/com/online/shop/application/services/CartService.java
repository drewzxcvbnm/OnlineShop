package com.online.shop.application.services;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.dto.PartialProductDto;
import com.online.shop.application.entities.Order;
import com.online.shop.application.entities.Product;
import com.online.shop.application.entities.Purchase;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.OrderMapper;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.OrderRepo;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class CartService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final UserService userService;
    private List<Product> cart = new ArrayList<>();

    public void addProductToCart(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(ItemNotFoundException.supplier("Cannot find item by Id:[%d]", productId));
        cart.add(product);
    }

    public List<PartialProductDto> getCartProducts() {
        return cart.stream()
                .map(productMapper::toPartialProductDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void submitOrder(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        order.setPurchases(getPurchasedProducts(order));
        order.setUser(userService.getCurrentUser().orElse(null));
        orderRepo.save(order);
        cart.clear();
    }

    private List<Purchase> getPurchasedProducts(Order order) {
        return cart.stream()
                .map(Product::getId)
                .map(productRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(pr -> new Purchase(order, pr))
                .collect(Collectors.toList());
    }

    public Long getCartSize() {
        return (long) cart.size();
    }

    public void clearCart() {
        cart.clear();
    }
}
