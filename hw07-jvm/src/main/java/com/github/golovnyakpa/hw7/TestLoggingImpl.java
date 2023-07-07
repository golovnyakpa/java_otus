package com.github.golovnyakpa.hw7;

import java.util.Arrays;

public class TestLoggingImpl implements TestLoggingInterface {

    @Log
    @Override
    public void calculation(Object arg) {
        System.out.println("Hello from single arg method\n");
    }

    @Log
    @Override
    public void calculation(Object arg1, Object arg2) {
        System.out.println("Hello from double arg method\n");
    }

    @Override
    public void calculation(Object arg1, Object arg2, Object arg3) {
        System.out.println("Hello from triple arg method\n");
    }

    @Override
    @Log
    public void calculation(Object... args) {
        System.out.println(Arrays.toString(args));
        System.out.println("Hello from varargs method\n");
    }

}
