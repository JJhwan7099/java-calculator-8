package calculator.view;

import calculator.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final Validator validator;

    public InputView(Validator validator) {
        this.validator = validator;
    }

    public String mainInputView() {
        String input;
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        input = Console.readLine();
        validator.validateInput(input);
        return input;
    }
}
