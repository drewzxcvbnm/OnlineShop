package com.online.shop.application.repositories;

import com.online.shop.application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.id=?1")
    Category getById(Long id);

}
