package com.integration;

import com.filter_stratergies.DayAndHour;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.types.OperatingHours;
import com.types.ResponseObj;
import com.types.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterDayAndHourIntegrationTest {
    private static Restaurant openAllDayMonday;
    private static Restaurant closedMonday;
    private static Restaurant notOpenInsideTime;

    @BeforeAll
    static void setupRestaurants() {
        OperatingHours o;

        openAllDayMonday = new Restaurant();
        o = new OperatingHours();
        o.setMonday("0:00 am - 12:00 am");
        openAllDayMonday.setOperatingHours(o);

        closedMonday = new Restaurant();
        o = new OperatingHours();
        o.setMonday("Closed");
        closedMonday.setOperatingHours(o);

        notOpenInsideTime = new Restaurant();
        o = new OperatingHours();
        o.setMonday("9:00 am - 10:00 am");
        notOpenInsideTime.setOperatingHours(o);
    }

    @Test
    void filterDayAndHourTest() {
        ResponseObj res = new ResponseObj();
        res.setRestaurants(new Restaurant[]{openAllDayMonday, closedMonday, notOpenInsideTime});

        List<Restaurant> result = new ArrayList<>();
        try {
            result = res.filter(new DayAndHour("Monday", 13, 0));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(1, result.size());
        assertEquals(openAllDayMonday, result.get(0));
    }

    @Test
    void filterDayAndHourNotFoundTest() {
        ResponseObj res = new ResponseObj();
        res.setRestaurants(new Restaurant[]{closedMonday, notOpenInsideTime});
        assertThrows(Exception.class, () -> res.filter(new DayAndHour("Monday", 13, 0)));
    }

    @Test
    void filterDayAndHourInvalidDayTest() {
        ResponseObj res = new ResponseObj();
        res.setRestaurants(new Restaurant[]{openAllDayMonday, closedMonday, notOpenInsideTime});
        assertThrows(Exception.class, () -> res.filter(new DayAndHour("Madeupday", 13, 0)));
    }
}
