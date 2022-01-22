package com.validator;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.myObject.MyObject;
import com.notification.ConsoleNotification;
import com.notification.Notification;
import com.rule.Rule;
import com.rule.RuleFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

public class TestLoadJson {
    public static void main(String[] args) {
    	JSONParser parser = new JSONParser(); 
        Notification notiConsole = new ConsoleNotification();
        ValidatorSchema schema = new ValidatorSchema(notiConsole);
        try {
            JSONObject objects = (JSONObject)parser.parse(new FileReader("C:\\Users\\Do Hoang Long\\Desktop\\objectStructure.json"));
            for(Object key: objects.keySet()) {
                String attrName = (String) key;
                JSONObject description = (JSONObject) objects.get(key);

                String type = (String) description.get("type");
                Validator validator =  ValidatorFactory.getInstance().getValidator(type);

                JSONObject objectRules = (JSONObject) description.get("rules");

                List<Rule> rules = new ArrayList<>();
                for(Object ruleName: objectRules.keySet()) {
                    String[] params = toStringArray((JSONArray) objectRules.get(ruleName));

                    Rule rule = RuleFactory.getInstance().getRule((String)ruleName, params);
                    rules.add(rule);
                }
                schema.addRule(attrName, validator, rules);
            }
            
            MyObject studentA = new MyObject();
            studentA.putAt("email", "sumsvprogmail.com" );
            studentA.putAt("age", 19);
            schema.validate(studentA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] toStringArray(JSONArray jsonArray) {
        if(jsonArray==null)
            return new String[]{};
        
        return jsonArray.toString().replace("[","").replace("]","").replace("\"","").split(",");
    }
}
