import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @Before
    public void setUp(){
        stringCalculator = new StringCalculator();
    }

    @Test
    public void ForEmptyString(){
        int sum = stringCalculator.add("");
        assertEquals(0, sum);
    }

    @Test
    public void ForSingleLatter(){
        int sum = stringCalculator.add("1");
        assertEquals(1, sum);
    }

    @Test
    public void ReturnSumOfTwoDigit(){
        int sum = stringCalculator.add("5,10");
        assertEquals(15, sum);
    }

}
