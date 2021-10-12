package com.online.shop.application.repositories;

import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.id=?1")
    Category getById(Long id);

    @Query(value = "select p.* from CATEGORY c "
            + "join PRODUCT P on c.ID = P.CATEGORY_ID "
            + "where c.ID=?1", nativeQuery = true)
    List<Product> getCategoryProducts(long categoryId);

}
