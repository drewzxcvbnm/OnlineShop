package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.product.ProductDeletionService;
import com.online.shop.application.services.product.ProductPersistenceService;
import com.online.shop.application.services.product.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    public static final String PRODUCT = "product";
    private final ProductRetrievalService productRetrievalService;
    private final ProductPersistenceService productPersistenceService;
    private final ProductRepo productRepo;
    private final ProductDeletionService productDeletionService;
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

    @PostMapping("/product/{id}")
    public String saveProduct(@PathVariable Long id,
                              @ModelAttribute ProductDto dto,
                              Model model) {
        Product product = productPersistenceService.updateProduct(id, dto);
        model.addAttribute(PRODUCT, product);
        return "product-page";
    }

    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute ProductDto dto,
                              Model model) {
        ProductDto product = productPersistenceService.createProduct(dto);
        model.addAttribute(PRODUCT, product);
        return "redirect:/product/" + product.getId();
    }


    @GetMapping("/product/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        ProductDto product = productRetrievalService.getProduct(productId);
        model.addAttribute(PRODUCT, product);
        return "edit-product";
    }

    @GetMapping("/product/{categoryId}/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId, @PathVariable long categoryId, Model model) {
        productDeletionService.deleteProduct(productId);
        return "redirect:/category/" + categoryId;
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        ProductDto product = new ProductDto();
        model.addAttribute(PRODUCT, product);
        return "edit-product";
    }

}
