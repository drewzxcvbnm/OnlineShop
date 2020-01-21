package com.online.shop.application.controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationExceptionHandlerTest {

    @Test
    public void handleItemNotFoundException() {
        ApplicationExceptionHandler handler = new ApplicationExceptionHandler();
        assertEquals("redirect:/", handler.handleItemNotFoundException(null, null));
    }
}