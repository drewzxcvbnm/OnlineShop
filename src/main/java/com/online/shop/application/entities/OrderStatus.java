package com.online.shop.application.entities;

import lombok.Getter;

public enum OrderStatus {
    PROCESSING("Processing Order Credentials"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    @Getter
    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
