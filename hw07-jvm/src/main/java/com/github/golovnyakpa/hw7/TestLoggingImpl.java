package com.github.golovnyakpa.hw7;

public class TestLoggingImpl implements TestLoggingInterface {

    public void calculation() {
        System.out.println("Hello from method without args\n");
    }

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
        System.out.println("Hello from varargs method\n");
    }

}
