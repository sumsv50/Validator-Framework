package com.rule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RuleFactory {
    private static RuleFactory instance = null;
    private static Map<RuleType, Rule> rules = new HashMap<>();

    private RuleFactory() {
        initialize();
    }

    private void initialize() {
        rules.put(RuleType.MIN, new Min());
        rules.put(RuleType.MAX, new Max());
        rules.put(RuleType.REQUIRED, new Required());
        rules.put(RuleType.PATTERN, new CustomPattern());
        rules.put(RuleType.IS_EMAIL, new IsEmail());
        rules.put(RuleType.IS_PHONE_NUMBER, new IsPhoneNumber());
    }

    public static RuleFactory getInstance() {
        if (instance == null) {
            instance = new RuleFactory();
        }

        return instance;
    }

    public Rule getRule(RuleType type) {
        if (rules.containsKey(type)) {
            return rules.get(type);
        }

        return null;
    }

    // Cũng chỉ để lấy được cái rule thôi (Factory)
    public Rule getRule(Field field, Annotation annotation) {
        switch (annotation.annotationType().getSimpleName()) {
            case "Max":
                com.annotation.Max max = field.getAnnotation(com.annotation.Max.class);
                return new Max(max.upperBound());
            case "Min":
                com.annotation.Min min = field.getAnnotation(com.annotation.Min.class);
                return new Min(min.lowerBound());
            case "Range":
                com.annotation.Range range = field.getAnnotation(com.annotation.Range.class);
                return new Range(range.lowerBound(), range.upperBound());
            case "CustomPattern":
                com.annotation.CustomPattern pattern = field.getAnnotation(com.annotation.CustomPattern.class);
                return new CustomPattern(pattern.regex(), pattern.flags());
            case "IsEmail":
                return new IsEmail();
            case "IsUserName":
                return new IsUserName();
            case "IsPhoneNumber":
                return new IsPhoneNumber();
            case "Required":
                return new Required();
            case "IsEven":
                return new IsEven();
            case "IsOdd":
                return new IsEven();
            case "IsPositive":
                return new IsPositive();
            case "IsNegative":
                return new IsNegative();
        }
        return null;
    }

    public Rule getRule(String name, String[] params) {
        switch (name.toLowerCase()) {
            case "require":
                return new Required();
            case "isemail":
                return new IsEmail();
            case "min": {
                int lowerBound = Integer.parseInt(params[0]);
                return new Min(lowerBound);
            }
            case "max": {
                int upperBound = Integer.parseInt(params[0]);
                return new Max(upperBound);
            }
            case "range": {
                int lowerBound = Integer.parseInt(params[0]);
                int upperBound = Integer.parseInt(params[1]);
                return new Range(lowerBound, upperBound);
            }
            case "regex": {
                String pattern = params[0];
                return new CustomPattern(pattern);
            }
            
            default:
                return null;
        }
    }
}
