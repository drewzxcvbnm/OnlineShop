package com.online.shop.application.controllers;

import com.online.shop.application.entities.Category;
import com.online.shop.application.repositories.CategoryRepo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private MainController mainController;
    @Mock
    private Model model;

    @Test
    public void mainPage() {
        List<Category> categoryList = new ArrayList<>();
        when(categoryRepo.findAll()).thenReturn(categoryList);
        Assertions.assertThat(mainController.mainPage(model))
                .isEqualTo("index");
        verify(model).addAttribute("categories", categoryList);
    }

    @Test
    public void login() {
        Assertions.assertThat(mainController.login())
                .isEqualTo("login");
    }
}