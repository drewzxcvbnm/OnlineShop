package com.online.shop.application.controllers;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registrationPage(UserDto userDto) {
        return "registration-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-page";
        }
        userService.saveNewUser(userDto);
        model.addAttribute("showMessage", true);
        model.addAttribute("message", "Successfully registered " + userDto.getUsername());
        return "index";
    }

}
