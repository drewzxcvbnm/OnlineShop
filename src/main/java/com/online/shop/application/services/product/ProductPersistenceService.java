package com.online.shop.application.services.product;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.CategoryRepo;
import com.online.shop.application.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPersistenceService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public Product updateProduct(Long productId, ProductDto dto) {
        Product product = productRepo.findById(productId)
                .orElseThrow(ItemNotFoundException::new);
        productMapper.updateEntity(product, dto);
        product.setCategory(categoryRepo.getById(dto.getCategory().getId()));
        productRepo.save(product);
        return product;
    }

    public ProductDto createProduct(ProductDto dto) {
        Product newProduct = productMapper.toEntity(dto);
        newProduct.setCategory(categoryRepo.getById(dto.getCategory().getId()));
        productRepo.save(newProduct);
        return productMapper.toProductDto(newProduct);
    }
}
