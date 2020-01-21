package com.online.shop.application.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

    @Mock
    private ObjectPostProcessor objectPostProcessor;
    @Mock
    private AuthenticationManagerBuilder authenticationBuilder;
    @Mock
    private Map<Class<?>, Object> map;
    private HttpSecurity security;

    @Before
    public void setup() {
        security = new HttpSecurity(objectPostProcessor, authenticationBuilder, map);
    }

    @Test
    public void configure() throws Exception {
        SecurityConfig securityConfig = new SecurityConfig();
        securityConfig.configure(security);
        assertNotNull(security);
    }
}