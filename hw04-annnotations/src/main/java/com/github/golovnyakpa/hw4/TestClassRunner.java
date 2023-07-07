package com.github.golovnyakpa.hw4;

import com.github.golovnyakpa.hw4.test_entities.TestEntity;
import com.github.golovnyakpa.hw4.test_entities.TestResult;
import com.github.golovnyakpa.hw4.tests_annotaions.After;
import com.github.golovnyakpa.hw4.tests_annotaions.Before;
import com.github.golovnyakpa.hw4.tests_annotaions.Test;
import com.github.golovnyakpa.hw4.utils.ReflectionHelper;
import com.github.golovnyakpa.hw4.utils.StatsPrinter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClassRunner {
    private final Class<?> classWithTests;

    public TestClassRunner(String classWithTests) {
        try {
            this.classWithTests = Class.forName(classWithTests);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    String.format("Class %s is not found in classpath%n", classWithTests)
            );
        }
    }

    public void runTests() {
        List<Method> beforeMethods =
                ReflectionHelper.findMethodsWithAnnotation(classWithTests, Before.class);
        List<Method> afterMethods =
                ReflectionHelper.findMethodsWithAnnotation(classWithTests, After.class);
        List<Method> tests =
                ReflectionHelper.findMethodsWithAnnotation(classWithTests, Test.class);
        Object testClassInstance = ReflectionHelper.createNewInstance(classWithTests);
        List<String> aborted = new ArrayList<>();
        List<String> failed = new ArrayList<>();
        List<TestResult> succeed = new ArrayList<>();
        for (Method test : tests) {
            TestResult res = new TestEntity(testClassInstance, beforeMethods, test, afterMethods).run();
            switch (res) {
                case SUCCEED -> succeed.add(res);
                case FAILED -> failed.add(test.getName());
                case ABORTED -> aborted.add(test.getName());
            }
        }
        StatsPrinter.print(aborted, failed, succeed);
    }

}
