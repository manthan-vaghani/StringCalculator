import com.google.common.base.Splitter;

import java.util.List;

public class StringCalculator {

    public int add(String inputString) {
        if("".equalsIgnoreCase(inputString)){
            return 0;
        }
        List<String> StringNumbers = Splitter.onPattern(",").splitToList(inputString);
        if(StringNumbers.size() == 1){
            return Integer.valueOf(inputString);
        }
        return Integer.valueOf(StringNumbers.get(0)) + Integer.valueOf(StringNumbers.get(1));
    }
}
