import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @Before
    public void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void ForEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void ForSingleLatter() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void ReturnSumOfTwoDigit() {
        assertEquals(15, stringCalculator.add("5,10"));
    }

    @Test
    public void ReturnSumOfUnknownAmountOfNumbers() {
        assertEquals(45, stringCalculator.add("6,9,3,5,8,7,2,4,1"));
    }

    @Test
    public void AcceptNewLineDelimiter() {
        assertEquals(20, stringCalculator.add("6,9\n5"));
    }

    @Test
    public void SupportDifferentDelimiter() {
        assertEquals(20, stringCalculator.add("//;\n6;9;5"));
    }

    @Test
    public void ForNegativeNumbers() {
        RuntimeException runtimeException = null;
        try {
            stringCalculator.add("6,-9,-3,5,-8,7,-2,4,-1");
        } catch (RuntimeException e) {
            runtimeException = e;
        }
        assertNotNull(runtimeException);
        assertEquals("Negatives Not Allowed: -9,-3,-8,-2,-1", runtimeException.getMessage());
    }

    @Test
    public void IgnoreNumbersBiggerThan1000() {
        assertEquals(24, stringCalculator.add("//;\n6;9;5;1001;1002;4"));
    }

    @Test
    public void AcceptSingleDelimiter() {
        assertEquals(20, stringCalculator.add("//[;]\n6;9;5"));
    }

    @Test
    public void AcceptMultipleDelimiter() {
        assertEquals(20, stringCalculator.add("//[;][*]\n6;9*5"));
    }

    @Test
    public void AcceptMultipleDelimiterWithAnyLength() {
        assertEquals(20, stringCalculator.add("//[***]\n6***9***5"));
    }
}
