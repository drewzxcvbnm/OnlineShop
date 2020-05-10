package com.online.shop.application.controllers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.mappers.OrderMapper;
import com.online.shop.application.services.CartService;
import com.online.shop.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @GetMapping("/content")
    public String getCartContents(Model model) {
        model.addAttribute("products", cartService.getCartProducts());
        return "cart";
    }

    @PostMapping("/add/{productId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable Long productId) {
        cartService.addProductToCart(productId);
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getCartSize() {
        return ResponseEntity.ok(cartService.getCartSize());
    }

    @GetMapping("/clear")
    public ModelAndView clearCart(Model model) {
        cartService.clearCart();
        return new ModelAndView("redirect:/cart/content");
    }

    @GetMapping("/checkout")
    public String checkout(OrderDto order) {
        userService.getCurrentUserInfo()
                .ifPresent(info -> orderMapper.updateOrderDto(order, info));
        return "form-order";
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute @Valid OrderDto order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "form-order";
        }
        cartService.submitOrder(order);
        model.addAttribute("showMessage", true);
        model.addAttribute("message", "Order has been successfully submitted");
        return "index";
    }


}
