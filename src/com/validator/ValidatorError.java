package com.validator;

public class ValidatorError {
    String field;
    String message;

    ValidatorError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String toString() {
        return String.format("%s: %s", this.field, this.message);
    }
}
