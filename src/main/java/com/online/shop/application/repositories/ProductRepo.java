package com.online.shop.application.repositories;

import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);
}
