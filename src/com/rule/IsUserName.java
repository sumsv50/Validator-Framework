package com.rule;

import java.util.regex.Pattern;

public class IsUserName extends CustomPattern {
    public IsUserName() {
        String regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        this.pattern = Pattern.compile(regex);
    }

    public String setErrorMessage() {
        return "Username is invalid";
    }
}
