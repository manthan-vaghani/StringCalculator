import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void ReturnSumOfUnknownAmountOfNumbers(){
        int sum = stringCalculator.add("6,9,3,5,8,7,2,4,1");
        assertEquals(45, sum);
    }

    @Test
    public void AcceptNewLineDelimiter(){
        int sum = stringCalculator.add("6,9\n5");
        assertEquals(20, sum);
    }

    @Test
    public void SupportDifferentDelimiter(){
        int sum = stringCalculator.add("//;\n6;9;5");
        assertEquals(20, sum);
    }

    @Test
    public void ForNegativeNumbers(){
        RuntimeException runtimeException = null;
        try {
            stringCalculator.add("6,-9,-3,5,-8,7,-2,4,-1");
        } catch (RuntimeException e) {
            runtimeException = e;
        }
        assertNotNull(runtimeException);
        assertEquals("Negatives Not Allowed: -9,-3,-8,-2,-1",runtimeException.getMessage());
    }

    @Test
    public void IgnoreNumbersBiggerThan1000(){
        int sum = stringCalculator.add("//;\n6;9;5;1001;1002;4");
        assertEquals(24, sum);
    }



}
