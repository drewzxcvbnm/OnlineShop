package com.online.shop.application.services.user;

import com.online.shop.application.entities.Purchase;
import com.online.shop.application.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserProductValidationService {

    private final UserService userService;

    public boolean userHasProduct(long productId) {
        Optional<User> currentUser = userService.getCurrentUser();
        return currentUser.stream()
                .flatMap(u -> u.getOrders().stream())
                .flatMap(order -> order.getPurchases().stream())
                .map(Purchase::getProduct)
                .anyMatch(p -> p.getId() == productId);
    }


}
