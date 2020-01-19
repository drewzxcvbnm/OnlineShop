package com.online.shop.application.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static Supplier<RuntimeException> supplier(String message, Object... args) {
        return () -> new UserNotFoundException(String.format(message, args));
    }
}
