package filter_stratergies;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.OperatingHours;
import types.Restaurant;

import static org.junit.jupiter.api.Assertions.*;

class DayAndHourTest {
    private static IFilter filter;
    private static IFilter filterFromString;
    private static IFilter invalidDayFilter;

    private static Restaurant mondayBeforeLunch;
    private static Restaurant closedOnMonday;
    private static Restaurant mondayBeforeAndAfterLunch;
    private static Restaurant mondayAllDay;
    private static Restaurant tuesdayAllDay;
    private static Restaurant missingOpeningHours;

    // Has to be done inside a before each as
    @BeforeAll
    static void setUp() {
        OperatingHours openHours = new OperatingHours();
        openHours.setMonday("9:00 am - 11:00 am");
        mondayBeforeLunch = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setMonday("Closed");
        closedOnMonday = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setMonday("9:00 am - 11:00 am, 1:00 pm - 6:00 pm");
        mondayBeforeAndAfterLunch = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setMonday("Open 24 hours");
        mondayAllDay = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setTuesday("1:00 am - 12:00 pm");
        tuesdayAllDay = new Restaurant(openHours);

        missingOpeningHours = new Restaurant();

        filter = new DayAndHour("Monday", 12, 0);
        filterFromString = new DayAndHour("Monday", "12", "0");
        invalidDayFilter = new DayAndHour("NotADay", 12, 0);
    }

    @Test
    void compareBeforeSetTime() {
        assertFalse(filter.doCompare(mondayBeforeLunch));
        assertFalse(filterFromString.doCompare(mondayBeforeLunch));
    }

    @Test
    void compareOnClosedDay() {
        assertFalse(filter.doCompare(closedOnMonday));
        assertFalse(filterFromString.doCompare(closedOnMonday));
    }

    @Test
    void compareBetweenTwoOpeningTimes() {
        assertFalse(filter.doCompare(mondayBeforeAndAfterLunch));
        assertFalse(filterFromString.doCompare(mondayBeforeAndAfterLunch));
    }

    @Test
    void compareNoDataOnDay() {
        assertFalse(filter.doCompare(tuesdayAllDay));
        assertFalse(filterFromString.doCompare(tuesdayAllDay));
    }

    @Test
    void compareMissingData() {
        assertFalse(filter.doCompare(missingOpeningHours));
        assertFalse(filterFromString.doCompare(missingOpeningHours));
    }

    @Test
    void compareOpenOnDayAndTime() {
        assertTrue(filter.doCompare(mondayAllDay));
        assertTrue(filterFromString.doCompare(mondayAllDay));
    }

    @Test
    void doCompareWithInvalidDay() {
        assertFalse(invalidDayFilter.doCompare(mondayAllDay));
    }

    @Test
    void invalidTimeThrowsError() {
        assertThrows(NumberFormatException.class, () -> new DayAndHour("Monday", "Twelve pm", "5"));
    }
}