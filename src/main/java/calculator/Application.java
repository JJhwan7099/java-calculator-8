package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {

        Set<String> delimiters = new HashSet<>();
        delimiters.add(",");
        delimiters.add(":");

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        if(isExistCustomDelimiter(input)) {
            delimiters.add(extractDelimiter(input));
            input = getStringWithoutCustomDelimiter(input);
        }

        String[] parts = splitByDelimiters(input, delimiters);
        int result = 0;
        for (String part : parts) {
            try {
                result = result + Integer.parseInt(part.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력값이 잘못되었습니다.");
            }
        }

        System.out.println("결과 : " + result);
    }

    private static String getStringWithoutCustomDelimiter(String input) {
        return input.split("\\\\n")[1].trim();
    }

    private static String[] splitByDelimiters(String str, Set<String> delimiters) {
        String result = "";
        String tmpStr = "";
        for (int i = 0; i < str.length(); i++) {
            if(!delimiters.contains(str.substring(i, i + 1))) {
                tmpStr += str.substring(i, i + 1);
                continue;
            }
            result += tmpStr + ",";
            tmpStr = "";
        }
        result += tmpStr;
        return result.split(",");
    }

    private static boolean isExistCustomDelimiter(String str) {
        String[] parts = str.split("\\\\n");
        if(parts.length != 2) {
            return false;
        }
        return parts[0].startsWith("//");
    }

    private static String extractDelimiter(String str) {
        String[] parts = str.split("\\\\n")[0].trim().split("//");
        if(parts.length != 2) {
            throw new IllegalArgumentException("구분자가 빈값입니다.");
        }
        return parts[1].trim();
    }
}
