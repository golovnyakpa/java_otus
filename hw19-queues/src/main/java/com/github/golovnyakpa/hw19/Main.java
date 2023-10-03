package com.github.golovnyakpa.hw19;

import com.github.golovnyakpa.hw19.collection.MonitorableCollectionImpl;

public class Main {
    public static void main(String[] args) {
        MonitorableCollectionImpl<Integer> coll = new MonitorableCollectionImpl<>();
        System.out.println(coll.add(1).add(2).add(42).delete(2).delete(3).delete(42).add(84));
    }
}
