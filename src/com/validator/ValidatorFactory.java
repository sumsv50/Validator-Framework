package com.validator;

public class ValidatorFactory {
    static ValidatorFactory instance = null;

    private ValidatorFactory() {}

    static public ValidatorFactory getInstance() {
        if(instance == null) {
            return new ValidatorFactory();
        }

        return instance;
    }

    public Validator getValidator(String type) {
      switch (type) {
          case "String":
              return new StringValidator();
          default:
              return new NumericValidator();
      }
    }
}
