package com.github.golovnyakpa.hw19.collection;

public interface MonitorableCollection<T> {
    MonitorableCollection<T> add(T elem);

    MonitorableCollection<T> delete(T elem);
}
