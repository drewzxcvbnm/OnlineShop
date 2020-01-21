package com.online.shop.application.services;

import com.online.shop.application.entities.User;
import com.online.shop.application.exceptions.UserNotFoundException;
import com.online.shop.application.repositories.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationUserDetailsServiceTest {

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Test(expected = UserNotFoundException.class)
    public void loadUserByUsername() {
        when(userRepo.findByUsername(anyString())).thenReturn(Optional.of(new User())).thenReturn(Optional.empty());
        applicationUserDetailsService.loadUserByUsername("name");
        verify(userRepo).findByUsername("name");
        applicationUserDetailsService.loadUserByUsername("name");
    }
}