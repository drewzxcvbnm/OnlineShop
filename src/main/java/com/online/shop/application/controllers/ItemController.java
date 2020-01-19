package com.online.shop.application.controllers;

import com.online.shop.application.entities.Category;
import com.online.shop.application.services.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ProductRetrievalService productRetrievalService;

    @GetMapping("/category/{category}")
    public String categoryProducts(@PathVariable Category category, Model model) {
        model.addAttribute("products", productRetrievalService.getProductsByCategory(category));
        model.addAttribute("category", category.getDisplayName());
        return "category-products";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRetrievalService.getProduct(id));
        return "product-page";
    }

}
