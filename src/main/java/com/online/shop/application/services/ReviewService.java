package com.online.shop.application.services;

import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.entities.ProductReview;
import com.online.shop.application.entities.User;
import com.online.shop.application.mappers.ReviewMapper;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.repositories.ReviewRepo;
import com.online.shop.application.repositories.UserRepo;
import com.online.shop.application.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;
    private final ReviewMapper reviewMapper;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;

    @Transactional
    public void saveReview(ReviewDto reviewDto, long productId) {
        String username = userService.getCurrentUsername();
        ProductReview productReview = reviewMapper.toProductReview(reviewDto);
        productReview.setProduct(productRepo.getById(productId));
        productReview.setUser(userRepo.getByUsername(username));
        reviewRepo.save(productReview);
    }

    @Transactional
    public void safeDeleteReview(long reviewId) {
        reviewRepo.findById(reviewId).ifPresent(entity -> {
            reviewRepo.delete(entity);
            entity.getProduct().getReviews().remove(entity);
            entity.getUser().getProductReviews().remove(entity);
        });
    }

    public boolean userCanDeleteReview(long reviewId) {
        Optional<ProductReview> review = reviewRepo.findById(reviewId);
        User user = userService.getCurrentUser().orElse(null);
        return userService.isAdmin() || review.map(r -> r.getUser() == user).orElse(true);
    }

}
