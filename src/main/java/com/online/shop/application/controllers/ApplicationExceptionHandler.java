package com.online.shop.application.controllers;

import com.online.shop.application.exceptions.ItemNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFoundException(Model model, Exception e) {
        return "redirect:/";
    }

}
