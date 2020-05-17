package com.online.shop.application.mappers;

import com.online.shop.application.dto.ReviewDto;
import com.online.shop.application.entities.ProductReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ReviewMapper {


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductReview toProductReview(ReviewDto reviewDto);

    List<ReviewDto> toReviewDtos(List<ProductReview> productReviews);

    @Mapping(target = "author", source = "user.username")
    ReviewDto toReviewDto(ProductReview productReview);

}
