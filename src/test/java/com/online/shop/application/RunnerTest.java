package com.online.shop.application;

import com.online.shop.application.repositories.ProductRepo;
import com.online.shop.application.repositories.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {

    @Mock
    private ProductRepo productRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private Runner runner;

    @Test
    public void run() throws Exception {
        runner.run();
        verify(productRepo, times(11)).save(any());
    }
}