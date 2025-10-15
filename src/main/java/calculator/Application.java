package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Scanner;
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
        if(!parts[0].startsWith("//")) {
            return false;
        }
        return true;
    }

    private static String extractDelimiter(String str) {
        String result = str.split("\\\\n")[0].trim().split("//")[1].trim();
        return result;
    }
}
