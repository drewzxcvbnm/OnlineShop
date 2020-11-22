package com.online.shop.application.mappers;

import com.online.shop.application.dto.CategoryDto;
import com.online.shop.application.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtos(List<Category> categories);


}
