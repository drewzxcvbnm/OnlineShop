package com.online.shop.application.controllers;


import com.online.shop.application.dto.UserDto;
import com.online.shop.application.services.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Model model;
    @InjectMocks
    private UserController userController;

    @Test
    public void registrationPage() {
        assertThat(userController.registrationPage(null))
                .isEqualTo("registration-page");
    }

    @Test
    public void registerUser() {
        UserDto userDto = new UserDto();
        when(bindingResult.hasErrors()).thenReturn(true).thenReturn(false);
        assertThat(userController.registerUser(userDto, bindingResult, model))
                .isEqualTo("registration-page");
        assertThat(userController.registerUser(userDto, bindingResult, model))
                .isEqualTo("index");
        verify(userService).saveNewUser(userDto);
        verify(model, times(2)).addAttribute(anyString(), any());
    }
}