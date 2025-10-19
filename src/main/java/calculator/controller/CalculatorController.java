package calculator.controller;

import calculator.model.Calculator;
import calculator.model.parser.InputParser;
import calculator.validator.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.HashSet;
import java.util.Set;

public class CalculatorController {

    private final Validator validator = new Validator();
    private final InputView inputView = new InputView(validator);
    private final OutputView outputView = new OutputView();
    private final Calculator calculator = new Calculator(validator);
    private final InputParser inputParser = new InputParser(validator);

    public void run() {
        String input = inputView.mainInputView();

        String[] numbers = inputParser.parse(input);
        int result = calculator.calculate(numbers);

        outputView.outputResult(result);
    }
}
