package com.validator;

import com.myObject.MyObject;
import com.notification.ConsoleNotification;
import com.notification.Notification;
import com.notification.NotificationFactory;
import com.rule.Rule;
import com.rule.RuleFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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
            Validator validator = ValidatorFactory.getInstance().getValidator(type.getSimpleName());

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

    public static ValidatorSchema loadSchemaFromFile(String filePath) {
        JSONParser parser = new JSONParser(); 
        try {
            JSONObject object = (JSONObject)parser.parse(new FileReader(filePath));

            String notificationType = (String) object.get("notification");
            Notification noti = NotificationFactory.getInstance().getNotification(notificationType);
            ValidatorSchema validatorSchema = new ValidatorSchema(noti);

            JSONObject objectSchema = (JSONObject)object.get("schema");
            for(Object key: objectSchema.keySet()) {
                String attrName = (String) key;
                JSONObject description = (JSONObject) objectSchema.get(key);

                String type = (String) description.get("type");
                Validator validator =  ValidatorFactory.getInstance().getValidator(type);

                JSONObject objectRules = (JSONObject) description.get("rules");

                List<Rule> rules = new ArrayList<>();
                for(Object ruleName: objectRules.keySet()) {
                    String[] params = toStringArray((JSONArray) objectRules.get(ruleName));

                    Rule rule = RuleFactory.getInstance().getRule((String)ruleName, params);
                    rules.add(rule);
                }
                validatorSchema.addRule(attrName, validator, rules);
            }
            
            return validatorSchema;
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleNotification clgNoti = new ConsoleNotification();
            clgNoti.notify("JSON schema structure is not valid, please double-check the documentation");
            return new ValidatorSchema(clgNoti);
        }
    }

    private static String[] toStringArray(JSONArray jsonArray) {
        if(jsonArray==null)
            return new String[]{};
        
        return jsonArray.toString().replace("[","").replace("]","").replace("\"","").split(",");
    }
}
