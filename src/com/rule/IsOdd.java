package com.rule;

public class IsOdd extends Rule{

    @Override
    public String validate(Double value) {
        String error = "";
        if(!value.isNaN() && value % 2 == 0) {
            error = "The value of the number must be odd";
        }

        return error;
    }
}
