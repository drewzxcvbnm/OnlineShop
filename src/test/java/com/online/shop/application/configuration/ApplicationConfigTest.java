package com.online.shop.application.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationConfigTest {

    ApplicationConfig config = new ApplicationConfig();

    @Test
    void passwordEncoder() {
        assertThat(config.passwordEncoder()).isInstanceOf(DelegatingPasswordEncoder.class);
    }
}