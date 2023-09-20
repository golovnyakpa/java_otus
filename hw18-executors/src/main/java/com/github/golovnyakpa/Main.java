package com.github.golovnyakpa;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(HwUtils::printElements);
        Thread t1 = new Thread(HwUtils::printElements);
        t0.setName("Thread 1");
        t1.setName("Thread 2");

        t0.start();
        Thread.sleep(500);
        t1.start();

        t0.join();
        t1.join();
    }
}
