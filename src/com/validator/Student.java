package com.validator;

import com.annotation.*;

public class Student {
    @Required
    @Max(upperBound = 30)
    String name;

    @Required
    @CustomPattern(regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    @IsEmail
    String email;

    @Required
    @IsPhoneNumber
    String phone;

    @Max(upperBound = 18)
    @Required
    int age;

    @Range(lowerBound = 0, upperBound = 10)
    @Required
    int gpa;

    Student(String name, String email, String phone, int age, int gpa) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gpa = gpa;
    }
}