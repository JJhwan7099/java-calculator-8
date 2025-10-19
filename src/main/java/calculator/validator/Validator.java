package calculator.validator;

public class Validator {
    public void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 잘못되었습니다.");
        }
    }

    public void validateCustomDelimiter(String delimiter) {

    }
}
