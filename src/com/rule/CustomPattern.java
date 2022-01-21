package com.rule;

import java.util.regex.Pattern;

public class CustomPattern extends Rule {
    protected Pattern pattern;

    public CustomPattern() {
        this.pattern = Pattern.compile("");
    }

    public CustomPattern(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public CustomPattern(String regex, int flags) {
        this.pattern = Pattern.compile(regex, flags);
    }

    public void setPattern(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public void setPattern(String regex, int flags) {
        this.pattern = Pattern.compile(regex, flags);
    }

    @Override
    public String validate(String value) {
        String error = "";
        if(!pattern.matcher(value).find()) {
            error = setErrorMessage();
        }

        return error;
    }

    public String setErrorMessage() {
        return "String is invalid";
    }
}
