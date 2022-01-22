package com.myObject;

import com.notification.ConsoleNotification;
import com.validator.Validator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyObject {
    public  Map<String, Object> attributes = new HashMap<String, Object>();

    public Object getAt(String attrName) {
        if (!attributes.containsKey(attrName)) {
            return null;
        }
        return attributes.get(attrName);
    }

    public void putAt(String attrName, Object value) {
        if (attributes.containsKey(attrName)) {
            attributes.replace(attrName, value);
            return;
        }
        attributes.put(attrName, value);
    }

    public List<String> getAllAttributeName() {
        return new ArrayList<String>(attributes.keySet());
    }

    public static MyObject loadObjectFromFile(String filePath) {
        MyObject object = new MyObject();
        JSONParser parser = new JSONParser(); 
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));

            for(Object key: jsonObject.keySet()) {
                String attrName = (String) key;
                String value = (String) jsonObject.get(key);
                object.putAt(attrName, value);
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleNotification clgNoti = new ConsoleNotification();
            clgNoti.notify("Object structure is not valid, please double-check the documentation");
            return new MyObject();
        }

    }
}
