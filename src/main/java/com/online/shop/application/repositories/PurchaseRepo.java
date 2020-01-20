package com.online.shop.application.repositories;

import com.online.shop.application.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
}
