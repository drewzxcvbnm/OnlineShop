package com.online.shop.application.exceptions;

import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }

    public static Supplier<RuntimeException> supplier(String message, Object... args) {
        return () -> new ItemNotFoundException(String.format(message, args));
    }
}
