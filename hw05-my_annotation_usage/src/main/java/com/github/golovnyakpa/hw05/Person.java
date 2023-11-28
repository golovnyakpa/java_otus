package com.github.golovnyakpa.hw05;

import com.github.golovnyakpa.hw05_processor.CustomToString;

@CustomToString
public class Person {

    private String name;
    private String secondName;
    private Integer age;

    public Person(String name, String secondName, Integer age) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

//    @CustomToString
    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                '}';
    }
}
