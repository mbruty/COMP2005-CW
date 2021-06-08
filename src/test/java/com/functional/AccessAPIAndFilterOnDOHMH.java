package com.functional;

import com.DataAccess;
import com.filter_stratergies.NeighbourhoodFilter;
import com.types.Neighborhood;
import com.types.ResponseObj;
import com.types.Restaurant;
import com.utils.DOHMHDsc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access  the  API  and  return  a  list  of  all  restaurants  in  a  specific  neighbourhood  according  to  their
// DOHMH inspection scores1 in a descending order.

public class AccessAPIAndFilterOnDOHMH {

    private static final Neighborhood TEST_NBHOOD = Neighborhood.Manhattan;
    private static Restaurant[] result;
    private static List<Restaurant> filtered;

    @BeforeAll
    static void setup() {
        String json = DataAccess.get();
        try {
            ResponseObj data = DataAccess.deserialize(json);
            filtered = data.filter(new NeighbourhoodFilter(TEST_NBHOOD));
            ResponseObj temp = new ResponseObj();
            temp.setRestaurants(filtered.toArray(new Restaurant[0]));
            result = temp.sort(new DOHMHDsc());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void resultHasData() {
        // Ensure that the result has data in it
        assertNotEquals(0, result.length);
    }

    @Test
    void filteredIsCorrect() {
        // Ensure that all results are inside the correct neighbourhood
        for (Restaurant r :
                filtered) {
            assertEquals(TEST_NBHOOD, r.getNeighborhood());
        }
    }

    @Test
    void itemsNotInFilteredDoNotMatchFilter() {
        // Ensure that all items not inside the filter don't match the correct neighbourhood
        for (Restaurant r :
                filtered) {
            // If filtered does not contain the restaurant, do the assertion
            if(!filtered.contains(r)) {
                assertNotEquals(TEST_NBHOOD, r.getNeighborhood());
            }
        }
    }

    @Test
    void itemsAreSorted() {
        // Ensure that the result is sorted correctly
        for (int i = 0; i < result.length - 1; i++) {
            if(result[i].getDohmhInspectionScore() == -1) {
                // If the dohmh score is null from the api, it gets set as -1...
                // Ensure that if it's -1 the one to the right is also -1
                assertEquals(-1, result[i + 1].getDohmhInspectionScore());
            } else if(result[i + 1].getDohmhInspectionScore() != -1) {
                // If the current dohmh score is not -1 and the one to the right is not -1,
                // Assert that it is in descending order
                assertTrue(result[i].getDohmhInspectionScore() <= result[i + 1].getDohmhInspectionScore());
            }
        }
    }
}
