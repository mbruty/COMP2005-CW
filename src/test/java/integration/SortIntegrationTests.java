package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Latlng;
import types.Neighborhood;
import types.ResponseObj;
import types.Restaurant;
import utils.DOHMHDsc;
import utils.KeyValuePair;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class SortIntegrationTests {

    private ResponseObj res;

    @BeforeEach
    void setup() {
        res = new ResponseObj();
    }

    @Test
    void sortOnDistancesTest() {
        Restaurant closest = new Restaurant();
        closest.setNeighborhood(Neighborhood.Manhattan);
        closest.setLatlng(new Latlng(40.753637, -73.988024));

        Restaurant middle = new Restaurant();
        middle.setNeighborhood(Neighborhood.Manhattan);
        middle.setLatlng(new Latlng(40.757773, -74.000574));

        Restaurant farthest = new Restaurant();
        farthest.setNeighborhood(Neighborhood.Manhattan);
        farthest.setLatlng(new Latlng(40.799969, -73.951659));

        res.setRestaurants(new Restaurant[]{middle, closest, farthest});

        KeyValuePair[] result = new KeyValuePair[0];
        try {
            result = Utils.getRestaurantsByDistance(Neighborhood.Manhattan, res);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Test that the result array is in the expected order
        assertEquals(closest, result[0].getKey());
        assertEquals(middle, result[1].getKey());
        assertEquals(farthest, result[2].getKey());

        // Test that all the distances are sorted
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue((Double) result[i].getValue() < (Double) result[i + 1].getValue());
        }
    }

    @Test
    void sortOnDOHMHTest() {

        Restaurant one = new Restaurant();
        one.setDohmhInspectionScore("1");

        Restaurant two = new Restaurant();
        two.setDohmhInspectionScore("2");

        Restaurant three = new Restaurant();
        three.setDohmhInspectionScore("3");

        Restaurant four = new Restaurant();
        four.setDohmhInspectionScore("4");

        Restaurant five = new Restaurant();
        five.setDohmhInspectionScore("5");

        res.setRestaurants(new Restaurant[]{two, four, one, five, three});
        Restaurant[] result = res.sort(new DOHMHDsc());

        // Test that the array is sorted
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i].getDohmhInspectionScore() < result[i + 1].getDohmhInspectionScore());
        }
    }
}
