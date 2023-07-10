package com.github.golovnyakpa.hw7;

public interface TestLoggingInterface {

    void calculation(Object arg);

    void calculation(Object arg1, Object arg2);

    void calculation(Object arg1, Object arg2, Object arg3);

    void calculation(Object... args);
}
