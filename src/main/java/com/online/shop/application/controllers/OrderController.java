package com.online.shop.application.controllers;

import com.online.shop.application.services.OrderService;
import com.online.shop.application.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/order/{orderId}/cancel")
    public String cancelOrder(Model m, @PathVariable long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/user/profile";
    }

}
