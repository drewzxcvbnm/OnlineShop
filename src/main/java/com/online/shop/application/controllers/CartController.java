package com.online.shop.application.controllers;

import com.online.shop.application.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/content")
    public String getCartContents(Model model) {
        model.addAttribute("products", cartService.getCartProducts());
        return "cart";
    }

    @PostMapping("/add/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable Long productId) {
        cartService.addProductToCart(productId);
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getCartSize() {
        return ResponseEntity.ok(cartService.getCartSize());
    }

    @GetMapping("/clear")
    public String clearCart(Model model) {
        cartService.clearCart();
        return getCartContents(model);
    }

}
