package com.rule;

public abstract class Rule {
    private Rule next;

    public Rule getNext() {
        return next;
    }

    public void setNext(Rule rule) {
        next = rule;
    }

    public String validate(Double value) {
        return "";
    }

    public String validate(String value) {
        return "";
    }
}
