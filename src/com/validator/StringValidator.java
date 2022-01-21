package com.validator;

import com.rule.Rule;

public class StringValidator extends Validator {

    @Override
    String excuteValidate(Rule rule, Object value) {
        String val = value != null
                ? value.toString()
                : "";

        return rule.validate(val);
    }
}
