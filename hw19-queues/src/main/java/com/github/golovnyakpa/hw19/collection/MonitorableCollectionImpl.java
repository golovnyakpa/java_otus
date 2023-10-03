package com.github.golovnyakpa.hw19.collection;

import com.github.golovnyakpa.hw19.collection.observer.Observer;
import com.github.golovnyakpa.hw19.conf.Conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonitorableCollectionImpl<T> implements MonitorableCollection<T> {

    private final List<T> collection;
    private final ArrayBlockingQueue<ActionDescr<T>> eventsQueue;

    public MonitorableCollectionImpl(List<T> collection) {
        this.collection = collection;
        eventsQueue = new ArrayBlockingQueue<>(Conf.eventsQueueCapacity);
        createObserver();
    }

    public MonitorableCollectionImpl() {
        this.collection = new ArrayList<>();
        eventsQueue = new ArrayBlockingQueue<>(Conf.eventsQueueCapacity);
        createObserver();
    }

    @Override
    public MonitorableCollection<T> add(T elem) {
        collection.add(elem);
        eventsQueue.add(new ActionDescr<>(Action.ADD, elem));
        return this;
    }

    @Override
    public MonitorableCollection<T> delete(T elem) {
        boolean isDeleted = collection.remove(elem);
        if (isDeleted) eventsQueue.add(new ActionDescr<>(Action.DELETE, elem));
        return this;
    }

    @Override
    public String toString() {
        return "MonitorableCollectionImpl{" +
                "collection=" + collection +
                '}';
    }

    private void createObserver() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Observer<T> observer = new Observer<>(eventsQueue);
        executor.submit(observer::poll);
    }
}
