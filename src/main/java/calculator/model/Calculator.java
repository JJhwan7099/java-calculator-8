package calculator.model;

import calculator.validator.Validator;

public class Calculator {

    private final Validator validator;

    public Calculator(Validator validator) {
        this.validator = validator;
    }

    public int calculate(String[] numbers) {
        int result = 0;
        for (String number : numbers) {
            try{
                result += Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력값이 잘못되었습니다.");
            }
        }
        return result;
    }
}
