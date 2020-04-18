package com.online.shop.application.entities;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EnumTest {

    @Test
    public void enumTest() {
        assertNotNull(Authority.values());
        assertNotNull(Authority.ADMIN.getRole());
    }

}