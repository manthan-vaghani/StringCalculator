import com.google.common.base.Splitter;

import java.util.List;

public class StringCalculator {

    public int add(String inputString) {
        if("".equalsIgnoreCase(inputString)){
            return 0;
        }
        List<String> StringNumbers = Splitter.onPattern(",|\n").splitToList(inputString);
        return StringNumbers.stream().mapToInt(Integer::valueOf).sum();
    }
}
