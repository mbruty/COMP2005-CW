package types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void stringToHoursPM() {
        try{
            int[] result = Date.stringToHoursMins("11:30 pm");
            assertArrayEquals(new int[] {23, 30}, result);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void stringToHoursAM() {
        try{
            int[] result = Date.stringToHoursMins("11:30 am");
            assertArrayEquals(new int[] {11, 30}, result);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void constructorTest() {
        Date d = new Date("9:00 am - 5:00 pm");
        assertEquals("9:0 - 17:0", d.toString());
    }

    @Test
    void constructorWithDayTest() {
        Date d = new Date("Sat - 5:00 pm");
        assertEquals("0:0 - 17:0", d.toString());
    }

    // The next two tests ensure that an invalid argument results in a thrown error.
    // All the possible reasons to throw an error are covered in the stringToHours tests
    @Test
    void constructorInvalidOpeningTimeTest() {
        assertThrows(IllegalArgumentException.class, () -> new Date("13:00 pm - 9:00 pm"));
    }

    @Test
    void constructorInvalidClosingTimeTest() {
        assertThrows(IllegalArgumentException.class, () -> new Date("9:00 am - 13:00 pm"));
    }

    @Test
    void constructorCloseBeforeOpenTest() {
        assertThrows(IllegalArgumentException.class, () -> new Date("10:00 am - 9:00 am"));
    }

    @Test
    void stringToHoursMissingAmOrPm() {
        assertThrows(Exception.class, () -> Date.stringToHoursMins("11:30"));
    }

    @Test
    void stringToHoursNotANumber() {
        assertThrows(Exception.class, () -> Date.stringToHoursMins("abc:30 am"));
    }

    @Test
    void stringToHoursEmptyString() {
        assertThrows(Exception.class, () -> Date.stringToHoursMins(""));
    }

    @Test
    void stringToHoursNegativeTime() {
        assertThrows(IllegalArgumentException.class, () -> Date.stringToHoursMins("-1:30 am"));
    }

    @Test
    void stringToHoursTooLargeHours() {
        assertThrows(IllegalArgumentException.class, () -> Date.stringToHoursMins("30:30 am"));
    }

    @Test
    void stringToHoursTooLargeMins() {
        assertThrows(IllegalArgumentException.class, () -> Date.stringToHoursMins("10:66 am"));
    }

    @Test
    void testToStringAm() {
        Date d = new Date("9:30 am - 11:30 am");
        assertEquals("9:30 - 11:30", d.toString());
    }

    @Test
    void testToStringPm() {
        Date d = new Date("1:30 pm - 5:30 pm");
        assertEquals("13:30 - 17:30", d.toString());
    }

    @Test
    void testToStringClosed() {
        Date d = new Date("Closed");
        assertEquals("Closed", d.toString());
    }

    @Test
    void isInsideTimeAmCloseHourTrue() {
        Date d = new Date("9:00 am - 10:30 am");
        assertTrue(d.isInsideTime(10, 29));
    }

    @Test
    void isInsideTimeAmCloseHourFalse() {
        Date d = new Date("9:00 am - 10:30 am");
        assertFalse(d.isInsideTime(10, 31));
    }

    @Test
    void isInsideTimeAmOpenHourTrue() {
        Date d = new Date("9:30 am - 10:30 am");
        assertTrue(d.isInsideTime(9, 30));
    }

    @Test
    void isInsideTimeAmOpenHourFalse() {
        Date d = new Date("9:30 am - 10:30 am");
        assertFalse(d.isInsideTime(9, 29));
    }

    @Test
    void isInsideTimePmTrue() {
        Date d = new Date("9:00 pm - 10:30 pm");
        assertTrue(d.isInsideTime(22, 0));
    }

    @Test
    void isInsideTimePmFalse() {
        Date d = new Date("9:00 am - 10:30 am");
        assertFalse(d.isInsideTime(11, 0));
    }

    @Test
    void isInsideTimeTrue() {
        Date d = new Date("9:00 am - 11:00 pm");
        assertTrue(d.isInsideTime(12, 0));
    }

    @Test
    void isInsideTimeClosed() {
        Date d = new Date("Closed");
        assertFalse(d.isInsideTime(11, 0));
    }
}