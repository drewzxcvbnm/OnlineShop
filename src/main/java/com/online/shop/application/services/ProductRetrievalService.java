package com.online.shop.application.services;

import com.online.shop.application.dto.CategoryProductDto;
import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRetrievalService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public List<CategoryProductDto> getProductsByCategory(Category category) {
        List<Product> products = productRepo.findAllByCategory(category);
        return products.stream()
                .map(productMapper::toCategoryProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(ItemNotFoundException.supplier("Cannot find item by Id:[%d]", productId));
        return productMapper.toProductDto(product);
    }


}
