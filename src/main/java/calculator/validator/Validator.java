package calculator.validator;

public class Validator {
    public void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 잘못되었습니다.");
        }
    }

    public void validateCustomDelimiter(String[] parts) {
        if(parts.length != 2) {
            throw new IllegalArgumentException("구분자가 빈값입니다.");
        }
    }

    public void validateParsedNumbers(String[] numbers) {
        for (String number : numbers) {
            System.out.println("number = " + number);
            if (number == null || number.isEmpty() || !Character.isDigit(number.charAt(0))) {
                throw new IllegalArgumentException("입력값이 잘못되었습니다.");
            }
            try {
                Integer.parseInt(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력값이 잘못되었습니다.");
            }
        }
    }
}
