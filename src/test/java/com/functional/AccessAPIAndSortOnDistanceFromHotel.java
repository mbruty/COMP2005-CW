package com.functional;

import com.DataAccess;
import com.types.Neighborhood;
import com.types.ResponseObj;
import com.types.Restaurant;
import com.utils.KeyValuePair;
import com.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access  the  API  and  return  a  list  of  all  restaurants  that  are  located  in  the  vicinity  of  an  hotel  in  a
// specific neighbourhood (sorted based on distance from the nearest to the farest within the district).

public class AccessAPIAndSortOnDistanceFromHotel {

    private static final Neighborhood TEST_NBHOOD = Neighborhood.Manhattan;
    private static ResponseObj data;
    private static KeyValuePair<Restaurant, Double>[] result;
    @BeforeAll
    static void setup() {
        String json = DataAccess.get();
        try {
            data = DataAccess.deserialize(json);
            result = Utils.getRestaurantsByDistance(TEST_NBHOOD, data);
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
    void resultsAreInCorrectNbhood() {
        // Ensure all items inside the result are in the correct neighbourhood
        for (KeyValuePair<Restaurant, Double> r :
                result) {
            assertEquals(TEST_NBHOOD, r.getKey().getNeighborhood());
        }
    }

    @Test
    void itemsNotInResultDoNotMatchFilter() {
        // Create a list that contains all the restaurants in the result
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (KeyValuePair<Restaurant, Double> r :
                result) {
            filteredRestaurants.add(r.getKey());
        }
        // Ensure all items that are not in the result are not in the neighbourhood
        for (Restaurant r:
                data.getRestaurants()) {
            if(!filteredRestaurants.contains(r)) {
                assertNotEquals(TEST_NBHOOD, r.getNeighborhood());
            }
        }
    }

    @Test
    void resultIsSorted() {
        // Ensure the array is sorted on distances
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i].getValue() <= result[i + 1].getValue());
        }
    }
}
