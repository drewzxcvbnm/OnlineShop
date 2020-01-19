package com.online.shop.application.entities;

import lombok.Getter;

@Getter
public enum Authority {
    USER("USER"),
    ADMIN("ADMIN");
    private String role;

    Authority(String role) {
        this.role = role;
    }
}
