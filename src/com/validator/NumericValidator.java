package com.validator;

import com.rule.Rule;

public class NumericValidator extends Validator{

    @Override
    String excuteValidate(Rule rule, Object value) {
        Double val = value !=null
                ? Double.parseDouble(value.toString())
                : Double.NaN;

        return rule.validate(val);
    }
}
