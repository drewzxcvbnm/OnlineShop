package com.online.shop.application.services;

import com.online.shop.application.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    private ValidationService validationService = new ValidationService();
    @Mock
    private Model model;


    @Test
    public void testIsValid() {
        assertFalse(validationService.isValid(order("and", "Zacs", "Visku 99", "IBAN123124"), model));
        assertTrue(validationService.isValid(order("Andris", "Zacs", "Visku 99", "IBAN123124"), model));
        assertFalse(validationService.isValid(order("Andris", "Zacs", "", "IBAN123124"), model));
    }

    private OrderDto order(String name, String surname, String address, String bank) {
        OrderDto dto = new OrderDto();
        dto.setCustomerName(name);
        dto.setCustomerSurname(surname);
        dto.setAddress(address);
        dto.setBankAccount(bank);
        return dto;
    }

}
