package com.online.shop.application.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

    @Mock
    private ObjectPostProcessor objectPostProcessor;
    @Mock
    private AuthenticationManagerBuilder authenticationBuilder;
    @Mock
    private ApplicationContext applicationContext;
    private HttpSecurity security;

    @Before
    public void setup() {
        Map<Class<?>, Object> map = new HashMap<>();
        map.put(ApplicationContext.class, applicationContext);
        when(applicationContext.getBeanNamesForType(GrantedAuthorityDefaults.class)).thenReturn(new String[]{});
        security = new HttpSecurity(objectPostProcessor, authenticationBuilder, map);
    }

    @Test
    public void configure() throws Exception {
        SecurityConfig securityConfig = new SecurityConfig();
        securityConfig.configure(security);
        assertNotNull(security);
    }
}