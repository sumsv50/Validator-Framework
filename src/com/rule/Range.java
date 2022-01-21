package com.rule;

public class Range extends Rule {
    double lowerBound;
    double upperBound;

    public Range(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Range() {
        this.lowerBound = Double.MIN_VALUE;
        this.upperBound = Double.MAX_VALUE;
    }

    void setLowerBound(Double lowerBound) {
        this.lowerBound = lowerBound;
    }

    void setUpperBound(Double upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String validate(Double value) {
        String error = "";
        if(!value.isNaN() && (value < lowerBound || value > upperBound)) {
            error = "The magnitude of the value must be in the range "
                    + String.format("(%4.2f, %4.2f)", lowerBound, upperBound);
        }

        return error;
    }
}
