package calculator.model.parser;

import calculator.validator.Validator;
import calculator.view.InputView;

public class InputParser {
    private final Validator validator;
    public InputParser(Validator validator) {
        this.validator = validator;
    }
}
