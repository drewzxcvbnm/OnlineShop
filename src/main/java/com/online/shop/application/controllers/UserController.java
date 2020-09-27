package com.online.shop.application.controllers;

import com.online.shop.application.dto.UserDto;
import com.online.shop.application.services.UserImageService;
import com.online.shop.application.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserImageService userImageService;

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/{userId}/image")
    @PreAuthorize("@userService.userIdIsCurrent(#userId)")
    public ResponseEntity<byte[]> getUserImage(@PathVariable long userId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(userImageService.getUserImage(userId));
    }

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
