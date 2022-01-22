package com.validator;

import com.myObject.MyObject;
import com.notification.ConsoleNotification;
import com.notification.Notification;
import com.notification.WindowNotification;
import com.rule.IsEmail;
import com.rule.Min;
import com.rule.Required;
import com.rule.Rule;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        //load schema from file
        // ValidatorSchema schemaLoadedFromFile = ValidatorSchema.loadSchemaFromFile("D:\\Java\\Validator-Framework\\src\\com\\validator\\schemaStructure.json");
        // MyObject studentA = MyObject.loadObjectFromFile("D:\\Java\\Validator-Framework\\src\\com\\myObject\\studentObject.json");
        // schemaLoadedFromFile.validate(studentA);

        

        // add schema by code
        Notification notiConsole = new ConsoleNotification();
        ValidatorSchema schema1 = new ValidatorSchema(notiConsole);

        schema1.addRule(
            "email",
            new StringValidator(),
            Arrays.asList(new Required(), new IsEmail())
        );

        schema1.addRule(
            "age",
            new NumericValidator(),
            Arrays.asList(new Required(),new Min(21))
        );
        MyObject studentA = MyObject.getCommonObjectByObjectName("Student");
        studentA.putAt("email", "sdfs@df@gmail.com");
        studentA.putAt("age", 12);
        schema1.validate(studentA);


        //******************************************************
        //******************************************************
        
        //Validate depend on object

        // ValidatorSchema schema2 = new ValidatorSchema(notiWindow);
        // Student student = new Student(
        //    "NguyenVanA",
        //    "a@gmail.com",
        //    "0986059068",
        //    20,
        //    12
        // );
//        schema2.validate(student);
    }
}
