package com.online.shop.application.controllers;

import com.online.shop.application.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryRepo categoryRepo;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
