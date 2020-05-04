package com.online.shop.application.controllers;

import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.services.product.ProductRetrievalService;
import com.online.shop.application.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.online.shop.application.controllers.ItemController.PRODUCT;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product/review")
public class ReviewsController {

    private final ProductRetrievalService productRetrievalService;
    private final ReviewService reviewService;

    @PostMapping("/{productId}")
    public String saveReview(@Valid ReviewDto reviewDto, BindingResult result,
                             @PathVariable Long productId, Model model) {
        if (!result.hasErrors()) {
            reviewService.saveReview(reviewDto, productId);
        }
        model.addAttribute(PRODUCT, productRetrievalService.getProduct(productId));
        return "product-page";
    }

}
