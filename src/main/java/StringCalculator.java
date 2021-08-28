import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String inputString) {
        if ("".equalsIgnoreCase(inputString)) {
            return 0;
        }
        List<String> StringNumbers = Splitter.onPattern(PatternSeparator(inputString)).omitEmptyStrings().splitToList(inputString.replace("//",""));

        NegativeNumberException(StringNumbers);

        return StringNumbers.stream().mapToInt(Integer::valueOf).filter(num -> num <= 1000).reduce(0, (num1, num2) -> num1 + num2);
    }

    private String PatternSeparator(String input) {
        return "[" + DelimiterPattern(input) + "|\n" + "]";
    }

    private String DelimiterPattern(String input) {

        if (input.startsWith("//")) {
            return String.valueOf(input.charAt(2));
        }
        return ",";
    }

    private void NegativeNumberException(List<String> StringNumbers) {
        List<Integer> NegativeStringNumbers = StringNumbers.stream().map(Integer::valueOf).filter(num -> num < 0).collect(Collectors.toList());
        if (NegativeStringNumbers.size() > 0) {
            throw new RuntimeException("Negatives Not Allowed: " + Joiner.on(",").join(NegativeStringNumbers));
        }
    }
}
