package com.online.shop.application.services;

import com.online.shop.application.dto.CategoryDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.mappers.CategoryMapper;
import com.online.shop.application.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesManager {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public List<CategoryDto> getAllCategoryDtos() {
        return categoryMapper.toCategoryDtos(categoryRepo.findAll());
    }

}
