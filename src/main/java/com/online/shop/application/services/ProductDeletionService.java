package com.online.shop.application.services;

import com.online.shop.application.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDeletionService {

    private final ProductRepo productRepo;

    public void deleteProduct(Long id) {
        try {
            productRepo.deleteById(id);
        } catch (Exception e) {
            log.error("Deletion error:", e);
        }
    }

}
