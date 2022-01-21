package com.rule;

public class IsNegative extends Rule{
    @Override
    public String validate(Double value) {
        String error = "";
        if(!value.isNaN() && value > 0) {
            error = "The value of the number must be negative";
        }

        return error;
    }
}
