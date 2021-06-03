package types;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatingHoursTest {

    private static OperatingHours o;

    @BeforeAll
    static void setup() {
        o = new OperatingHours();
    }

    private void dateArrayEquals(Date[] expected, Date[] actual) {
        String[] expectedToString = new String[expected.length];
        String[] actualToString = new String[actual.length];

        for (int i = 0; i < expected.length; i++) {
            expectedToString[i] = expected[i].toString();
        }

        for (int i = 0; i < actual.length; i++) {
            actualToString[i] = actual[i].toString();
        }

        assertArrayEquals(expectedToString, actualToString);
    }

    @Test
    void stringToDateArrayTwentyFourHours() {
        Date[] result = o.stringToDateArray("Open 24 hours");

        dateArrayEquals(new Date[] { new Date("0:00 am - 12:00 pm") }, result);
    }

    @Test
    void stringToDateArrayOneValue() {
        Date[] result = o.stringToDateArray("9:00 am - 1:30 pm");
        dateArrayEquals(new Date[] { new Date("9:00 am - 1:30 pm") }, result);
    }

    @Test
    void stringToDateArrayTwoValues() {
        Date[] result = o.stringToDateArray("9:00 am - 1:30 pm, 2:30 pm - 6:30 pm");
        dateArrayEquals(new Date[] { new Date("9:00 am - 1:30 pm"), new Date("2:30 pm - 6:30 pm") }, result);
    }

}