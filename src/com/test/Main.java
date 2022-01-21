package com.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Student student = new Student();
        for (Field field: student.getClass().getDeclaredFields()) {
            Class type = field.getType();
            String name = field.getName();
            System.out.println(type);
            System.out.println(name);
            System.out.println(field.get(student));
            System.out.println("-------------");

            for(Annotation annotation : field.getDeclaredAnnotations()) {
                System.out.println(annotation);
                System.out.println(annotation.annotationType());

                System.out.println(annotation.annotationType().getSimpleName());

                switch(annotation.annotationType().getSimpleName()) {
                    case "Max":
                        Max max = field.getAnnotation(Max.class);
                        System.out.println(max.upperBound());
                        break;
                    case "Min":
                        Min min = field.getAnnotation(Min.class);
                        System.out.println(min.lowerBound());
                        break;
                }
                System.out.println("-------------");
            }

//            System.out.println("-------------");
//            Min min = field.getAnnotation(Min.class);
//            if (min != null)
//                System.out.println(min.lowerBound());
        }
    }
}
