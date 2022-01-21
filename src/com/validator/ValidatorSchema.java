package com.validator;

import com.myObject.MyObject;
import com.notification.Notification;
import com.rule.Rule;
import com.rule.RuleFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorSchema {
	private Notification noti;
    private Map<String, Validator> validators = new HashMap<>();

    ValidatorSchema(Notification notification)
	{
		this.noti = notification;
	}

    public void addRule(String attrName, Validator validator, List<Rule> rules) {
        for(Rule rule : rules) {
            validator.addRule(rule);
        }

        if (validators.containsKey(attrName)) {
           validators.replace(attrName, validator);
        } else {
            validators.put(attrName, validator);
        }
    }

    //Validate for map
    boolean validate(MyObject obj) {
        List<String> attrNameList = obj.getAllAttributeName();
        for(String key : attrNameList) {
            if(validators.containsKey(key)) {
                Object value = obj.getAt(key);
                String message = validators.get(key).validate(value);

                //Neu co loi thong bao
                if(!message.isEmpty()) {
                    noti.notify(key, message);
                }
            }
        }
        return true;
    }

    //Validate for specified object
    boolean validate(Object obj) throws IllegalAccessException {
        List<ValidatorError> errors = new ArrayList<>();

        //Duyet qua tung gia tri thuoc tinh cua doi tuong nhan vao
        for (Field field: obj.getClass().getDeclaredFields()) {
            Class type = field.getType(); //Lay kieu cua thuoc tinh day
            String name = field.getName(); //Lay ten cua thuoc tinh day

            //Dua vao type cua thuoc tinh khoi tao ra dung loai validator de su dung
            Validator validator = ValidatorFactory.getInstance().getValidator(type);

            //Duyet qua cac annotation cua thuoc tinh hien tai
            //Va them cac loai rule tuong ung voi annotation
            for(Annotation annotation : field.getDeclaredAnnotations()) {
                Rule rule = RuleFactory.getInstance().getRule(field, annotation);
                validator.addRule(rule);
            }

            String message = validator.validate(field.get(obj));

            //Neu co loi thong bao
            if(!message.isEmpty()) {
//                errors.add(new ValidatorError(name, message));
//                System.out.println(new ValidatorError(name, message));
            	ValidatorError temp = new ValidatorError(name, message);
                errors.add(temp);
//                System.out.println(new ValidatorError(key, message));
                noti.notify("ERROR", temp.toString());
            }

        }
        return true;
    }
}
