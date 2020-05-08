package com.online.shop.application.controllers;

import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.services.product.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemController {

    public static final String PRODUCT = "product";
    private final ProductRetrievalService productRetrievalService;
    private final CategoryRepo categoryRepo;

    @GetMapping("/category/{categoryId}")
    public String categoryProducts(@PathVariable long categoryId, Model model) {
        Category category = categoryRepo.getById(categoryId);
        model.addAttribute("products", productRetrievalService.getProductsByCategory(category));
        model.addAttribute("category", category.getDisplayName());
        model.addAttribute("cat", category);
        return "category-products";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        model.addAttribute(PRODUCT, productRetrievalService.getProduct(id));
        model.addAttribute("reviewDto", new ReviewDto());
        return "product-page";
    }

}
