package icrperusa.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DivisionTest {

    @Test
    public void testModule() {
        int num1 = 12;
        int num2 = 7;
        int result = (num1/num2);
        assertEquals(1, result);
        result = (num1 % num2);
        assertEquals(5, result);
    }



}
