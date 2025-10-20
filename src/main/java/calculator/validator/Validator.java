package calculator.validator;

public class Validator {
    public void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 잘못되었습니다.");
        }
    }

    public void validateExtractCustomDelimiter(String[] parts) {
        if(parts.length < 2) {
            throw new IllegalArgumentException("커스텀 구분자가 빈값입니다.");
        }
    }

    public void validateCustomDelimiter(String delimiter) {
        if (delimiter == null || delimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 1자리 문자입니다.");
        }
    }

    public void validateParsedNumbers(String[] numbers) {
        for (String number : numbers) {
            if (number == null || number.isEmpty() || !Character.isDigit(number.charAt(0))) {
                throw new IllegalArgumentException("계산해야할 문자열 부분이 잘못되었습니다.");
            }
            try {
                Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("계산해야할 숫자는 양의 정수입니다.");
            }
        }
    }
}
