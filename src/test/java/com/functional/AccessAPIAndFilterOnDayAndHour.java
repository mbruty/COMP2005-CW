package com.functional;

import com.DataAccess;
import com.filter_stratergies.DayAndHour;
import com.types.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access the API and return a list of all restaurants that are open on a specific day and hour.

public class AccessAPIAndFilterOnDayAndHour {
    private static final int TEST_HOUR = 12;
    private static final int TEST_MINS = 12;
    private static List<Restaurant> result = new ArrayList<>();
    private static ResponseObj data;


    @BeforeAll
    static void setup() {

        String json = DataAccess.get();
        try {
            data = DataAccess.deserialize(json);
            result = data.filter(new DayAndHour("Monday", TEST_HOUR, TEST_MINS));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void resultHasData() {
        // Ensure that the result has data in it
        assertNotEquals(0, result.size());
    }

    @Test
    void resultFilterIsCorrect() {
        // Assert that every restaurant in the result is expected from the filter
        for (Restaurant r :
                result) {
            Date[] openingHours = r.getOperatingHours().getMonday();
            boolean oneMatches = false;
            for (Date d:
                    openingHours) {
                if(d.getOpening()[0] <= TEST_HOUR && d.getClosing()[0] > TEST_HOUR) {
                    oneMatches = true;
                }
            }
            assertTrue(oneMatches);
        }
    }

    @Test
    void itemsNotInResultDoNotMatchFilter() {
        // Assert that every item in the original dataset that is not in the result does not match
        // the filter
        for (Restaurant r :
                data.getRestaurants()) {
            boolean oneMatches = false;
            if(!result.contains(r)){
                for (Date d :
                        r.getOperatingHours().getMonday()) {
                    if(d.getOpening()[0] <= TEST_HOUR && d.getClosing()[0] > TEST_HOUR) {
                        oneMatches = true;
                    }
                }
            }
            assertFalse(oneMatches);
        }
    }
}
