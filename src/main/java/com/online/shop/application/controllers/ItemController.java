package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.ProductDeletionService;
import com.online.shop.application.services.ProductPersister;
import com.online.shop.application.services.ProductRetrievalService;
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

    private final ProductRetrievalService productRetrievalService;
    private final ProductPersister productPersister;
    private final ProductRepo productRepo;
    private final ProductDeletionService productDeletionService;

    @GetMapping("/category/{category}")
    public String categoryProducts(@PathVariable Category category, Model model) {
        model.addAttribute("products", productRetrievalService.getProductsByCategory(category));
        model.addAttribute("category", category.getDisplayName());
        model.addAttribute("cat", category);
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

    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute ProductDto dto,
                              Model model) {
        ProductDto product = productPersister.createProduct(dto);
        model.addAttribute("product", product);
        return "redirect:/product/" + product.getId();
    }


    @GetMapping("/product/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        ProductDto product = productRetrievalService.getProduct(productId);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @GetMapping("/product/{category}/{id}/delete")
    public String deleteProduct(@PathVariable Long id, @PathVariable Category category, Model model) {
        productDeletionService.deleteProduct(id);
        return "redirect:/category/" + category;
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        ProductDto product = new ProductDto();
        model.addAttribute("product", product);
        return "edit-product";
    }

}
