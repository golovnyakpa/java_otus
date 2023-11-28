package com.github.golovnyakpa.hw05;

import com.github.golovnyakpa.hw05.Person;
public class PersonPrintable extends Person {
    @Override
    public String toString() {
        return "Person{name="+ getName() + "," + "secondName="+ getSecondName() + "," + "age="+ getAge() + "}";
    }
}

