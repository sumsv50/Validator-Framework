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
//        Rule rule = new Rule() {
//            @Override
//            public String validate(Double value) {
//                if(value %2 != 0) return "So khong chia het cho 2";
//                return "";
//            }
//
//            @Override
//            public String validate(String value) {
//                return "DMM";
//            }
//        };

        MyObject studentA = new MyObject();
        studentA.putAt("email", "sumsvprogmail.com" );
        studentA.putAt("age", 20);

        // Notification notiWindow = new WindowNotification();
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
