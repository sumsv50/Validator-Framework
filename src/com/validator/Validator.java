package com.validator;

import com.rule.Rule;
import com.rule.RuleFactory;
import com.rule.RuleType;

public abstract class Validator {
    Rule head;
    Rule pointer;

    void addRule(RuleType type) {
        RuleFactory ruleFactory = RuleFactory.getInstance();
        Rule rule = ruleFactory.getRule(type);
        if(head == null) {
            head = rule;
            pointer = head;
        }
        else {
            this.pointer.setNext(rule);
            this.pointer = rule;
        }
    }

    void addRule(Rule rule) {
        if(head == null) {
            head = rule;
            pointer = head;
        }
        else {
            this.pointer.setNext(rule);
            this.pointer = rule;
        }
    }


    String validate(Object value) {
        Rule temp = head;
        String message = "";
        while (temp!=null && message.isEmpty()) {
            message = excuteValidate(temp, value);
            temp = temp.getNext();
        }

        return message;
    }

    abstract String excuteValidate(Rule rule, Object value);
}
