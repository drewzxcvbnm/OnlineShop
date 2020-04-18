package com.online.shop.application;

import com.online.shop.application.entities.Category;

import java.util.Arrays;
import java.util.List;

public class TestBaseUtils {

    public static Category PORTABLE_ELECTRONICS = new Category(1L, "pe.png", "Portable electronics", null);
    public static Category COMPUTERS = new Category(2L, "c.png", "Computers", null);
    public static Category VIDEO_GAMES = new Category(3L, "vg.png", "Video games", null);
    public static Category AUTOMOBILE = new Category(4L, "a.png", "Automobile", null);
    public static Category FURNITURE = new Category(5L, "f.png", "Furniture", null);
    public static Category HOUSEHOLD_APPLIANCES = new Category(6L, "ha.png", "Household appliances", null);
    public static Category CLOTHES = new Category(7L, "cl.png", "Clothes", null);
    public static Category COSMETICS = new Category(8L, "cm.png", "Cosmetics", null);
    public static List<Category> categoryList = Arrays.asList(PORTABLE_ELECTRONICS, COMPUTERS, VIDEO_GAMES, AUTOMOBILE, FURNITURE, HOUSEHOLD_APPLIANCES, CLOTHES, COSMETICS);
}
