package com.myObject;

import com.validator.Validator;

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
}
