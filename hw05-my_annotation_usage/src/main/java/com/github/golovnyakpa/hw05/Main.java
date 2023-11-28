package com.github.golovnyakpa.hw05;

public class Main {
    public static void main(String[] args) {
        var p = new PersonPrintable();
        p.setAge(2);
        p.setName("Vasya");
        p.setSecondName("Ivanov");
        System.out.println(p); // Person{name=Vasya,secondName=Ivanov,age=2}
    }
}
