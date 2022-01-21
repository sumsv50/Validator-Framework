package com.test;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestLoadJson {
    public static void main(String[] args) {
    	JSONParser parser = new JSONParser(); 
        try {
            JSONObject objects = (JSONObject)parser.parse(new FileReader("C:\\Users\\Do Hoang Long\\Desktop\\objectStructure.json"));
            for(Object key: objects.keySet()) {
                String fieldName = (String) key;

                
            }
            System.out.println(((JSONObject)obj.get("email")).get("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
