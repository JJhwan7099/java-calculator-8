package calculator.model.parser;

import calculator.validator.Validator;
import calculator.view.InputView;

import java.util.HashSet;
import java.util.Set;


public class InputParser {
    private final Validator validator;
    public InputParser(Validator validator) {
        this.validator = validator;
    }

    public String[] parse(String input) {
        Set<String> delimiters = new HashSet<>();
        delimiters.add(",");
        delimiters.add(":");

        String targetString = input;
        String[] numbers;

        if(isExistCustomDelimiter(input)) {
            delimiters.add(extractDelimiter(input));
            targetString = getStringWithoutCustomDelimiter(input);
        }

        numbers = splitByDelimiters(targetString, delimiters);
        return numbers;
    }

    private String[] splitByDelimiters(String targetString, Set<String> delimiters) {
        String result = "";
        String token = "";
        for (int i = 0; i < targetString.length(); i++) {
            if(!delimiters.contains(targetString.substring(i, i + 1))) {
                token += targetString.substring(i, i + 1);
                continue;
            }
            result += token + ",";
            token = "";
        }
        result += token;

        String[] numbers = result.split(",");
        validator.validateParsedNumbers(numbers);
        return numbers;
    }

    private String getStringWithoutCustomDelimiter(String input) {
        return input.split("\\\\n")[1].trim();
    }

    private String extractDelimiter(String input) {
        String[] parts = input.split("\\\\n")[0].trim().split("//");
        validator.validateCustomDelimiter(parts);
        return parts[1].trim();
    }

    private boolean isExistCustomDelimiter(String str) {
        String[] parts = str.split("\\\\n");
        if(parts.length != 2) {
            return false;
        }
        return parts[0].startsWith("//");
    }
}
