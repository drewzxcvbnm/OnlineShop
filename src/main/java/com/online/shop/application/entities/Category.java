package com.online.shop.application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    PORTABLE_ELECTRONICS("Portable electronics", "pe.png"),
    COMPUTERS("Computers", "c.png"),
    VIDEO_GAMES("Video games", "vg.png"),
    AUTOMOBILE("Automobile", "a.png"),
    FURNITURE("Furniture", "f.png"),
    HOUSEHOLD_APPLIANCES("Household appliances", "ha.png"),
    CLOTHES("Clothes", "cl.png"),
    COSMETICS("Cosmetics", "cm.png");

    private String displayName;
    private String img;

}
