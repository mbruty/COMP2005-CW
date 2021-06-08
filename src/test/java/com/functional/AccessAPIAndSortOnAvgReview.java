package com.functional;

import com.DataAccess;
import com.filter_stratergies.RatingInNeighbourhood;
import com.types.Neighborhood;
import com.types.ResponseObj;
import com.types.Restaurant;
import com.types.Review;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access the API and return a list of all restaurants in a specific neighbourhood with average review
//ratings higher than a specific rating.
public class AccessAPIAndSortOnAvgReview {

    private static final Neighborhood TEST_NBHOOD = Neighborhood.Manhattan;
    private static final float MIN_RATING = 4f;
    private static List<Restaurant> result;
    private static ResponseObj data;

    @BeforeAll
    static void setup() {
        String json = DataAccess.get();
        try {
            data = DataAccess.deserialize(json);
            result = data.filter(new RatingInNeighbourhood(MIN_RATING, TEST_NBHOOD));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void resultHasData() {
        assertNotEquals(0, result.size());
    }

    @Test
    void filterIsCorrect() {
        for (Restaurant r :
                result) {
            float average = calcAverage(r);
            // Assert that the average rating is greater than or equal the minimum
            assertTrue(MIN_RATING <= average);

            // Assert that the restaurant is in the correct restaurant
            assertEquals(TEST_NBHOOD, r.getNeighborhood());
        }
    }

    @Test
    void itemsNotInFilteredAreNotInNbhoodOrAreBelowMinRating() {
        for (Restaurant r :
                data.getRestaurants()) {
            if(!result.contains(r)) {
                float average = calcAverage(r);
                // If this condition fails, then the test will pass
                // as the restaurant is not inside the TEST_NBHOOD
                if(r.getNeighborhood() == TEST_NBHOOD) {
                    // If it is inside the neighbourhood, assert that  the average is below
                    // the min
                    assertTrue(average < MIN_RATING);
                }
            }
        }
    }

    private static float calcAverage(Restaurant r) {
        float accumulator = 0;
        for (Review rev :
                r.getReviews()) {
            accumulator += rev.getRating();
        }

        return accumulator / r.getReviews().length;
    }
}
