package com.rule;

public class Max extends Rule {
    int upperBound;

    public Max() {
        this.upperBound = 0;
    }

    public Max(int upperBound) {
        this.upperBound = upperBound;
    }

    public void setUperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String validate(Double value) {
        String error = "";
        if(!value.isNaN() && value > upperBound) {
            error = "The magnitude of the excess value " + upperBound;
        }

        return error;
    }

    @Override
    public String validate(String value) {
        String error = "";
        if(value.length()  > upperBound) {
            error = "The size of the String exceeds " + upperBound +" characters";
        }

        return error;
    }
}
