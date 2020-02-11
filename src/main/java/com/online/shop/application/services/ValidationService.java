package com.online.shop.application.services;

import com.online.shop.application.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ValidationService {

    public boolean isValid(OrderDto order, Model model) {
        String namePattern = "[A-Z][a-z]+";
        boolean nameMatch = Pattern.matches(namePattern, order.getCustomerName());
        boolean surnameMatch = Pattern.matches(namePattern, order.getCustomerSurname());
        boolean addressMatch = !order.getAddress().isEmpty();
        boolean bankMatch = !order.getBankAccount().isEmpty();
        model.addAttribute("nameMatch", !nameMatch);
        model.addAttribute("surnameMatch", !surnameMatch);
        model.addAttribute("bankMatch", !bankMatch);
        model.addAttribute("addressMatch", !addressMatch);
        return Stream.of(nameMatch, surnameMatch, addressMatch, bankMatch)
                .reduce(true, (a, b) -> a && b);
    }

}
