package filter_stratergies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.OperatingHours;
import types.Restaurant;

import static org.junit.jupiter.api.Assertions.*;

class DayAndHourTest {
    private IFilter filter;
    private Restaurant mondayBeforeLunch;
    private Restaurant mondayBeforeAndAfterLunch;
    private Restaurant mondayAllDay;
    private Restaurant tuesdayAllDay;
    private Restaurant missingOpeningHours;
    @BeforeEach
    void setUp() {
        OperatingHours openHours = new OperatingHours();
        openHours.setMonday("9:00 am - 11:00 am");
        this.mondayBeforeLunch = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setMonday("9:00 am - 11:00 am, 13:00 pm - 6:00 pm");
        this.mondayBeforeAndAfterLunch = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setMonday("Open 24 hours");
        this.mondayAllDay = new Restaurant(openHours);

        openHours = new OperatingHours();
        openHours.setTuesday("1:00am - 12:00pm");
        this.tuesdayAllDay = new Restaurant(openHours);

        this.missingOpeningHours = new Restaurant();

        this.filter = new DayAndHour("Monday", 12, 0);
    }

    @Test
    void doCompare() {
        assertFalse(filter.doCompare(mondayBeforeLunch));
        assertFalse(filter.doCompare(mondayBeforeAndAfterLunch));
        assertFalse(filter.doCompare(tuesdayAllDay));
        assertFalse(filter.doCompare(missingOpeningHours));

        assertTrue(filter.doCompare(mondayAllDay));
    }
}