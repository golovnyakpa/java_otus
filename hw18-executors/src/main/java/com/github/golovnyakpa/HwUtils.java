package com.github.golovnyakpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HwUtils {

    private static final Object lock = new Object();

    private static List<Integer> getRange(Integer start, Integer end) {
        return IntStream
                .rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Integer> getMirroredList(Integer start, Integer end) {
        List<Integer> lstStart = getRange(start, end);
        List<Integer> resultList = new ArrayList<>(lstStart);
        lstStart.remove(lstStart.size() - 1);
        Collections.reverse(lstStart);
        resultList.addAll(lstStart);
        return resultList;
    }

    public static void printElements() {
        synchronized (lock) {
            List<Integer> mirroredList = getMirroredList(1, 10);
            int listSize = mirroredList.size();
            for (int i = 0; i < listSize; i++) {
                HwUtils.sleep(100);
                System.out.printf("%s: %s \n", Thread.currentThread().getName(), mirroredList.get(i));
                lock.notify();
                try {
                    if (i < listSize - 1) lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
