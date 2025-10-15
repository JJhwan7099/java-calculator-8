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

        String[] parts = splitByDelimiters(input, delimiters);
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
}
