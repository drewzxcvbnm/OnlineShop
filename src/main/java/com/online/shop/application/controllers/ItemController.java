package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.services.ProductPersister;
import com.online.shop.application.services.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ProductRetrievalService productRetrievalService;
    private final ProductPersister productPersister;

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

    @PostMapping("/product/{id}")
    public String saveProduct(@PathVariable Long id,
                              @ModelAttribute ProductDto dto,
                              Model model) {
        Product product = productPersister.updateProduct(id, dto);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/product/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        ProductDto product = productRetrievalService.getProduct(productId);
        model.addAttribute("product", product);
        return "edit-product";
    }

}
