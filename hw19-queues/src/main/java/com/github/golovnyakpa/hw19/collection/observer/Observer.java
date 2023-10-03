package com.github.golovnyakpa.hw19.collection.observer;

import com.github.golovnyakpa.hw19.collection.Action;
import com.github.golovnyakpa.hw19.collection.ActionDescr;
import com.github.golovnyakpa.hw19.conf.Conf;
import com.github.golovnyakpa.hw19.utils.Utils;

import java.util.concurrent.ArrayBlockingQueue;

public class Observer<T> {

    private final ArrayBlockingQueue<ActionDescr<T>> eventsQueue;

    public Observer(ArrayBlockingQueue<ActionDescr<T>> eventsQueue) {
        this.eventsQueue = eventsQueue;
    }

    public void poll() {
        while (true) {
            try {
                ActionDescr<T> action = eventsQueue.take();
                if (action.action() == Action.ADD) {
                    Utils.writeToFile(action.elem(), Conf.addedPath);
                } else {
                    Utils.writeToFile(action.elem(), Conf.deletedPath);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
