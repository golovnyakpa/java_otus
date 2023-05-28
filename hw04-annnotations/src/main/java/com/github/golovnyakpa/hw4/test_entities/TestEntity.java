package com.github.golovnyakpa.hw4.test_entities;

import com.github.golovnyakpa.hw4.utils.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.List;

public class TestEntity {
    final Object testClassInstance;
    final List<Method> beforeMethods;
    final Method test;
    final List<Method> afterMethods;

    public TestEntity(Object testClassInstance, List<Method> beforeMethods,
                      Method test, List<Method> afterMethods) {
        this.testClassInstance = testClassInstance;
        this.beforeMethods = beforeMethods;
        this.test = test;
        this.afterMethods = afterMethods;
    }

    public TestResult run() {
        try {
            ReflectionHelper.runMethods(testClassInstance, beforeMethods);
        } catch (Throwable e) {
            return TestResult.ABORTED;
        }
        TestResult testRes = ReflectionHelper.runTest(testClassInstance, test);
        try {
            ReflectionHelper.runMethods(testClassInstance, afterMethods);
        } catch (Throwable e) {
            return TestResult.ABORTED;
        }
        return testRes;
    }
}
