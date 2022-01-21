package com.rule;

import java.util.regex.Pattern;

public class IsEmail extends CustomPattern {
    public IsEmail() {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public String setErrorMessage() {
        return "Email is invalid";
    }
}
