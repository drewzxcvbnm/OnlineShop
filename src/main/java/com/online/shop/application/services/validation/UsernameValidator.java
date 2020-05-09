package com.online.shop.application.services.validation;

import com.online.shop.application.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepo userRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepo.findByUsername(value).isEmpty();
    }
}
