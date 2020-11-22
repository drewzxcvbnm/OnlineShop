package com.online.shop.application.repositories;

import com.online.shop.application.entities.Product;
import com.online.shop.application.entities.User;
import com.online.shop.application.exceptions.ItemNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product getById(Long id);

    @Query(value = "select u.* from PRODUCT p " +
            "join PURCHASE P2 on p.ID = P2.PRODUCT_ID " +
            "join \"order\" o on P2.\"order_id\" = o.ID " +
            "join USER U on o.USER_ID = U.ID\n" +
            "where p.ID=?1", nativeQuery = true)
    List<User> getAllProductOwners();

    default Product findObligatoryProduct(long productId) {
        return findById(productId)
                .orElseThrow(ItemNotFoundException.supplier("Cannot find item by Id:[%d]", productId));
    }

}
