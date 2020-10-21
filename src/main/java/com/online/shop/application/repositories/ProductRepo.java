package com.online.shop.application.repositories;

import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product getById(Long id);

    default Product findObligatoryProduct(long productId) {
        return findById(productId)
                .orElseThrow(ItemNotFoundException.supplier("Cannot find item by Id:[%d]", productId));
    }

}
