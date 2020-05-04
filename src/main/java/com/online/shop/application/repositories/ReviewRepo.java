package com.online.shop.application.repositories;

import com.online.shop.application.entities.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<ProductReview, Long> {
}
