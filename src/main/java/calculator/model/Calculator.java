package calculator.model;

import calculator.validator.Validator;

public class Calculator {

    private final Validator validator;

    public Calculator(Validator validator) {
        this.validator = validator;
    }
}
