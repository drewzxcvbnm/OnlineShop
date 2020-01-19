package com.online.shop.application.controllers;

import com.online.shop.application.entities.Category;
import com.online.shop.application.entities.Product;
import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.services.ProductRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductRepo repo;
    private final ProductRetrievalService productRetrievalService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        SecurityContext context = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
        System.out.println(authorities);
        return ResponseEntity.ok("done");
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
