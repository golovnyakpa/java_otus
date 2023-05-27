package com.github.golovnyakpa;

public class Fruit {

    double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return String.format("%s %s kg", className, fmtDouble(this.weight));
    }

    private static String fmtDouble(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    public double getWeight() {
        return weight;
    }
}
