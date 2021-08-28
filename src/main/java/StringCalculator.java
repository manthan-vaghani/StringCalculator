import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class StringCalculator {

    public int add(String inputString) {
        if ("".equalsIgnoreCase(inputString)) {
            return 0;
        }
        List<String> StringNumbers = Splitter.onPattern(PatternSeparator(inputString)).omitEmptyStrings()
                .splitToList(inputString.replace("//", "").replace("[", "")
                        .replace("]", ""));

        NegativeNumberException(StringNumbers);

        return StringNumbers.stream().mapToInt(Integer::valueOf).filter(num -> num <= 1000).reduce(0, (num1, num2) -> num1 + num2);
    }

    private String PatternSeparator(String input) {
        return "[" + Joiner.on("|").join(DelimiterPattern(input)) + "|\n" + "]";
    }

    private List<String> DelimiterPattern(String input) {

        if (input.startsWith("//")) {
            if (input.contains("[")) {
                return DelimiterClass(input);
            }
            return asList(String.valueOf(input.charAt(2)));
        }
        return asList(",");
    }

    private List<String> DelimiterClass(String input){
        ArrayList<String> delimiters = new ArrayList<>();
        int startIdx = 0;
        int opnBracketIdx = input.indexOf("[",startIdx);
        while(opnBracketIdx >= 0){
            int closeBracketIdx = input.indexOf("]",startIdx);
            delimiters.add(input.substring(opnBracketIdx+1,closeBracketIdx));
            startIdx = closeBracketIdx + 1;
            opnBracketIdx = input.indexOf("[",startIdx);
        }
        return delimiters;
    }

    private void NegativeNumberException(List<String> StringNumbers) {
        List<Integer> NegativeStringNumbers = StringNumbers.stream().map(Integer::valueOf).filter(num -> num < 0).collect(Collectors.toList());
        if (NegativeStringNumbers.size() > 0) {
            throw new RuntimeException("Negatives Not Allowed: " + Joiner.on(",").join(NegativeStringNumbers));
        }
    }
}