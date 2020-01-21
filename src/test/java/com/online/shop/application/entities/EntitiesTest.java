package com.online.shop.application.entities;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNull;

public class EntitiesTest {

    @Test
    public void testEntities() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        testEntity(Product.builder().build());
        testEntity(User.builder().build());
        testEntity(Order.builder().build());
        testEntity(Purchase.builder().build());
        Purchase purchase = new Purchase(null, null);
        assertNull(purchase.getProduct());
    }

    private <T> void testEntity(T entity) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> entityClass = entity.getClass();
        for (Method declaredMethod : entityClass.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            int parameterCount = declaredMethod.getParameterCount();
            Object[] arr = new Object[parameterCount];
            declaredMethod.invoke(entity, arr);
        }
        for (Constructor<?> constructor : entityClass.getConstructors()) {
            Object[] arr = new Object[constructor.getParameterCount()];
            constructor.newInstance(arr);
        }

    }

}