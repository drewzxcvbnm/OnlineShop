package com.online.shop.application.configuration;

import com.online.shop.application.entities.Authority;
import com.online.shop.application.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationUserPrincipalTest {

    @Test
    public void testApplicationUserPrincipalTest() {
        User user = User.builder()
                .authority(Authority.ADMIN)
                .password("pass")
                .username("name")
                .id(1L)
                .build();
        ApplicationUserPrincipal principal = new ApplicationUserPrincipal(user);
        assertTrue(principal.isAccountNonExpired());
        assertTrue(principal.isAccountNonLocked());
        assertTrue(principal.isCredentialsNonExpired());
        assertTrue(principal.isEnabled());
        assertEquals("name", principal.getUsername());
        assertEquals("{bcrypt}pass", principal.getPassword());
        assertEquals("ROLE_ADMIN", principal.getAuthorities().toArray()[0].toString());

    }

}