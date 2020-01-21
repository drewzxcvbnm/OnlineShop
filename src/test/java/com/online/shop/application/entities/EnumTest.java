package com.online.shop.application.entities;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EnumTest {

    @Test
    public void enumTest() {
        assertNotNull(Authority.values());
        assertNotNull(Category.values());
        assertNotNull(Authority.ADMIN.getRole());
        assertNotNull(Category.COMPUTERS.getDisplayName());
        assertNotNull(Category.COMPUTERS.getImg());
    }

}