package com.rule;

public class Required extends Rule {

    @Override
    public String validate(String value) {
        String error = "";
        if(value.isEmpty()) {
            error = "Is not empty";
        }

        return error;
    }

    @Override
    public String validate(Double value) {
        String error = "";
        if(value.isNaN()) {
            error = "Is not empty";
        }

        return error;
    }
}
