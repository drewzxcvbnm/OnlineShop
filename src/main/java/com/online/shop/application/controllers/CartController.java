package com.online.shop.application.controllers;

import com.online.shop.application.dto.OrderDto;
import com.online.shop.application.services.CartService;
import com.online.shop.application.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ValidationService validationService;

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
    public String checkout(Model model) {
        OrderDto orderDto = new OrderDto();
        model.addAttribute("order", orderDto);
        return "form-order";
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute OrderDto orderDto, Model model) {
        if (!validationService.isValid(orderDto, model)) {
            model.addAttribute("order", orderDto);
            return "form-order";
        }
        cartService.submitOrder(orderDto);
        model.addAttribute("showMessage", true);
        model.addAttribute("message", "Order has been successfully submitted");
        return "index";
    }


}
