package com.github.golovnyakpa.hw4;

import com.github.golovnyakpa.hw4.tests_annotaions.After;
import com.github.golovnyakpa.hw4.tests_annotaions.Before;
import com.github.golovnyakpa.hw4.tests_annotaions.Test;

public class TestClass {

    @Before
    public void init() {
        System.out.println("Hello from stage before test");
    }

//    @Before
//    public void initAgain() {
//        throw new RuntimeException("blah");
//    }

    @After
    public void after() {
        System.out.println("Goodbye from stage after test");
    }

//    @After
//    public void afterAgain() {
//        System.out.println("Goodbye from stage after test 2");
//        throw new RuntimeException("blah");
//    }

    @Test
    public void fstFailedTest() {
        System.out.println("this test should fails");
        throw new AssertionError("test with exception was launched");
    }

    @Test
    public void fstAbortedTest() {
        System.out.println("this test should be aborted 1");
        throw new RuntimeException("test with exception was launched");
    }

    @Test
    public void fstSuccessTest() {
        System.out.println("first test running");
    }

    @Test
    public void sndSuccessTest() {
        System.out.println("snd test running");
    }

    @Test
    public void sndAbortedTest() {
        System.out.println("this test should be aborted 2");
        throw new RuntimeException("test with exception was launched");
    }

}
