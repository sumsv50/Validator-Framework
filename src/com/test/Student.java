package com.test;

public class Student {
    @Max(upperBound = 64)
    @Min(lowerBound = 18)
    int age;

    public int b;
    Student() {
        age = 10;
    }

    public void  setAge(int age){
        this.age = age;
    }
    public int getAge() {
        return age;
    }
}