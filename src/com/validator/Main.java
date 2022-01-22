package com.validator;

import com.myObject.MyObject;
import com.notification.ConsoleNotification;
import com.notification.Notification;
import com.notification.WindowNotification;
import com.rule.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        // load schema from file
        // ValidatorSchema schemaLoadedFromFile = ValidatorSchema.loadSchemaFromFile("D:\\Java\\Validator-Framework\\src\\com\\validator\\schemaStructure.json");
        // MyObject studentA = MyObject.loadObjectFromFile("D:\\Java\\Validator-Framework\\src\\com\\myObject\\studentObject.json");
        // schemaLoadedFromFile.validate(studentA);


        RuleFactory ruleFactory = RuleFactory.getInstance();

        ruleFactory.addCustomRule("IsAvailablePromote", new Rule() {
            @Override
            public String validate(Double value) {
                if (value >= 12) {
                    return "";
                };
                return "Is not available to promote";
            }
            @Override
            public String validate(String value) {
                return "Invalid type";
            }
        });
        // add schema by code
        Notification notiConsole = new ConsoleNotification();
        ValidatorSchema schema1 = new ValidatorSchema(notiConsole);

        schema1.addRule(
            "age",
            new NumericValidator(),
            Arrays.asList(new Required(),new Min(21))
        );

        schema1.addRule(
            "email",
            new StringValidator(),
            Arrays.asList(new Required(),new IsEmail())
        );

        schema1.addRule(
                "class",
                new NumericValidator(),
                Arrays.asList(new Required(), ruleFactory.getCustomRule("IsAvailablePromote"))
        );

        MyObject studentA = MyObject.getCommonObjectByObjectName("Student");
        studentA.putAt("email", "sdfs@df@gmail.com");
        studentA.putAt("age", 12);
        studentA.putAt("class", 10);
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
