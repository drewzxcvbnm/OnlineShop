package com.online.shop.application.controllers;

import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.services.ReviewService;
import com.online.shop.application.services.product.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@userProductValidationService.userHasProduct(#productId)")
    public String saveReview(@Valid ReviewDto reviewDto, BindingResult result,
                             @PathVariable Long productId, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("reviewDto", new ReviewDto());
            reviewService.saveReview(reviewDto, productId);
            return "redirect:/product/" + productId;
        }
        model.addAttribute(PRODUCT, productRetrievalService.getProduct(productId));
        return "product-page";
    }

}
