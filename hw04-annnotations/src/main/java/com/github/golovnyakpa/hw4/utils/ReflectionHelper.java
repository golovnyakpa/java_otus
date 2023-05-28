package com.github.golovnyakpa.hw4.utils;

import com.github.golovnyakpa.hw4.test_entities.TestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {
    public static List<Method> findMethodsWithAnnotation(
            Class<?> cls,
            Class<? extends Annotation> annotationClass) {
        List<Method> annotatedMethods = new ArrayList<>();

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }

    public static Object createNewInstance(Class<?> cls) {
        try {
            return cls.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Can't create new instance of test class you provided. " +
                    "It should be possible for methods invocation");
        }
    }

    public static void runMethods(Object instance, List<Method> methods)
            throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.invoke(instance);
        }
    }

    public static TestResult runTest(Object instance, Method method) {
        try {
            method.invoke(instance);
            return TestResult.SUCCEED;
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertionError) {
                return TestResult.FAILED;
            } else {
                return TestResult.ABORTED;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Test method is not accessible");
        }
    }
}
