package com.github.golovnyakpa.hw7;


public class Main {
    public static void main(String[] args) {
        TestLoggingInterface inst = MyProxy.createInstance();
        inst.calculation();
        inst.calculation(1);
        inst.calculation(1, 2);
        inst.calculation(1, "2");
        inst.calculation(1, 2, 3);
        inst.calculation(1, 2, 3, 4);
    }
}
