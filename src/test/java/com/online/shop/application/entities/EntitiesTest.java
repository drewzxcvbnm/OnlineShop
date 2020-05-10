package com.online.shop.application.entities;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertNull;

public class EntitiesTest {

    private final static List<String> methodExclusions = Arrays.asList("setPurchases");

    @Test
    public void testEntities() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        testEntity(Product.builder().build());
        testEntity(User.builder().build());
        testEntity(Order.builder().build());
        testEntity(Purchase.builder().build());
        testEntity(ProductReview.builder().build());
        testEntity(UserInfo.builder().build());
        testEntity(Category.builder().build());
        Purchase purchase = new Purchase(null, null);
        assertNull(purchase.getProduct());
    }

    private <T> void testEntity(T entity) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> entityClass = entity.getClass();
        Stream.of(entityClass.getDeclaredMethods())
                .filter(m -> methodExclusions.stream().noneMatch(ex -> m.getName().contains(ex)))
                .peek(m -> m.setAccessible(true))
                .forEach(m -> testMethod(entity, m));
        for (Constructor<?> constructor : entityClass.getConstructors()) {
            Object[] arr = new Object[constructor.getParameterCount()];
            constructor.newInstance(arr);
        }

    }

    private <T> void testMethod(T entity, Method declaredMethod) {
        int parameterCount = declaredMethod.getParameterCount();
        Object[] arr = new Object[parameterCount];
        ReflectionUtils.invokeMethod(declaredMethod, entity, arr);
    }

}