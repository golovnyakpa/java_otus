package com.github.golovnyakpa.hw9.bridge.shape;

import com.github.golovnyakpa.hw9.bridge.color.Color;

public abstract class Shape {
    protected final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract public String draw();
}
