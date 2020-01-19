package com.online.shop.application.services;

import com.online.shop.application.dto.ProductDto;
import com.online.shop.application.entities.Product;
import com.online.shop.application.exceptions.ItemNotFoundException;
import com.online.shop.application.mappers.ProductMapper;
import com.online.shop.application.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPersister {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    public Product updateProduct(Long productId, ProductDto dto) {
        Product product = productRepo.findById(productId)
                .orElseThrow(ItemNotFoundException::new);
        productMapper.updateEntity(product, dto);
        productRepo.save(product);
        return product;
    }
}
