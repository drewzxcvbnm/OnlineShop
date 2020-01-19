package com.online.shop.application.controllers;

import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductRepo repo;
    private final ProductRetrievalService productRetrievalService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        repo.save(Product.builder().name("Pr1").price(BigDecimal.TEN).category(Category.COMPUTERS).properties(Arrays.asList("prop1", "prop2")).build());
        List<Product> all = repo.findAll();
        System.out.println(all);
        return ResponseEntity.ok("done");
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

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
