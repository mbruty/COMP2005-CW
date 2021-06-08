package com.functional;

import com.DataAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.filter_stratergies.CuisineInNeighbourhood;
import com.types.Neighborhood;
import com.types.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.types.ResponseObj;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access  the  API  and  return  a  list  of  all  restaurants  of  a  specific  type  of  cuisine  in  a  specific
//neighbourhood.
public class AccessAPIAndFilterByNeighbourhoodAndCuisineTest {

    private static ResponseObj data;
    private static List<Restaurant> result = new ArrayList<>();
    private static final Neighborhood TEST_NBHOOD = Neighborhood.Manhattan;
    private static final String CUISINE = "Asian";

    @BeforeAll
    static void setup() {
        String json = DataAccess.get();
        try {
            data = DataAccess.deserialize(json);
            result = data.filter(new CuisineInNeighbourhood(CUISINE, TEST_NBHOOD));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void resultHasData() {
        // Assert that we have data
        assertNotEquals(0, result.size());
    }

    @Test
    void restaurantIsInNeighbourhood() {
        // Assert that every restaurant in the result is expected from the filter
        for (Restaurant r :
                result) {
            assertEquals(TEST_NBHOOD, r.getNeighborhood());
            assertEquals(CUISINE, r.getCuisineType());
        }
    }

    @Test
    void nonFilteredItemsAreOutsideNeighbourhoodOrAreNotCuisine() {
        // Assert that every item in the original dataset that is not in the result does not match
        // the filter
        for (Restaurant r :
                data.getRestaurants()) {
            if(!result.contains(r)){
                boolean restaurantIsNotInNeighbourhoodOrIsNotCuisine = !r.getCuisineType().equals("Asian") || r.getNeighborhood() != Neighborhood.Manhattan;
                assertTrue(restaurantIsNotInNeighbourhoodOrIsNotCuisine);
            }
        }
    }
}
