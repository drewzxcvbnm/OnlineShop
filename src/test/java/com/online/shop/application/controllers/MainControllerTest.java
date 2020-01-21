package com.online.shop.application.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    private MainController mainController = new MainController();
    @Mock
    private Model model;

    @Test
    public void mainPage() {
        Assertions.assertThat(mainController.mainPage(model))
                .isEqualTo("index");
    }

    @Test
    public void login() {
        Assertions.assertThat(mainController.login())
                .isEqualTo("login");
    }
}