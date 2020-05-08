package com.online.shop.application.controllers;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import com.online.shop.application.services.product.ProductDeletionService;
import com.online.shop.application.services.product.ProductPersistenceService;
import com.online.shop.application.services.product.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.online.shop.application.controllers.ItemController.PRODUCT;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductRetrievalService productRetrievalService;
    private final ProductDeletionService productDeletionService;
    private final ProductPersistenceService productPersistenceService;

    @GetMapping("/product/{productId}/edit")
    public String editProduct(@PathVariable Long productId, Model model) {
        ProductDto product = productRetrievalService.getProduct(productId);
        model.addAttribute(PRODUCT, product);
        return "edit-product";
    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        ProductDto product = new ProductDto();
        model.addAttribute(PRODUCT, product);
        return "edit-product";
    }

    @GetMapping("/product/{categoryId}/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId, @PathVariable long categoryId, Model model) {
        productDeletionService.deleteProduct(productId);
        return "redirect:/category/" + categoryId;
    }


    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute(PRODUCT) @Valid ProductDto dto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "edit-product";
        }
        Product product = productPersistenceService.updateProduct(id, dto);
        model.addAttribute(PRODUCT, product);
        return "redirect:/product/" + id;
    }


    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute(PRODUCT) @Valid ProductDto dto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "edit-product";
        }
        ProductDto product = productPersistenceService.createProduct(dto);
        model.addAttribute(PRODUCT, product);
        return "redirect:/product/" + product.getId();
    }

}

