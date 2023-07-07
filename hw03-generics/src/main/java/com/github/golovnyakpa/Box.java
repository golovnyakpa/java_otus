package com.github.golovnyakpa;

import java.util.*;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    public Box(List<? extends T> fruits) {
        this.fruits = new ArrayList<>(fruits);
    }

    public Box(T... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public Box<T> addFruit(T fruit) {
        this.fruits.add(fruit);
        return this;
    }

    public int fruitsNum() {
        return this.fruits.size();
    }

    public double weight() {
        return fruits.stream().reduce(0.0, (acc, curr) -> acc + curr.getWeight(), Double::sum);
    }

    public <A extends Fruit> boolean compare(Box<A> other) {
        return Math.abs(this.weight() - other.weight()) < Utils.doubleOpsPrecision;
    }

    // tbd ideal would be to make:
    // public <A super T> Box<A> moveContentToOtherBox(Box<A> other)
    public void moveContentToOtherBox(Box<? super T> other) {
        if (this.equals(other)) {
            System.out.println("Can't move to the same box");
        } else {
            for (T el : this.fruits) {
                other.addFruit(el);
            }
            this.fruits.clear();
        }
    }

    public List<T> getFruits() {
        return this.fruits;
    }
}
