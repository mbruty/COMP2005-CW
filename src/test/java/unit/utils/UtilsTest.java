package unit.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import types.*;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsTest {

    private static Hotel[] hotels;

    @BeforeAll
    static void setup() {
        hotels = new Hotel[]{
                new Hotel("Manhattan", 40.752831, -73.985748),
                new Hotel("Queens", 40.753990, -73.949240),
                new Hotel("Brooklyn", 40.689510, -73.988100),
        };
    }

    // Extra sanity check that the hotel locations haven't changed
    @Test
    void getHotels() {
        Hotel[] h = Utils.getHotels();
        for (int i = 0; i < hotels.length; i++) {
            assertEquals(hotels[i].getNeighborhood(), h[i].getNeighborhood());
            assertEquals(hotels[i].getLatlng().getLat(), h[i].getLatlng().getLat());
            assertEquals(hotels[i].getLatlng().getLng(), h[i].getLatlng().getLng());
        }
    }

    @Test
    void calcDistance() {
        // Two points in Plymouth
        Latlng posOne = new Latlng(50.375278, -4.139263);
        Latlng posTwo = new Latlng(50.364250, -4.141810);

        // Distance in meters calculated using an online tool
        assertEquals(1239.491601446147, Utils.calcDistance(posOne, posTwo));
    }

    @Test
    void calcDistanceFirstNull() {
        // Two null points
        Latlng posOne = new Latlng();
        Latlng posTwo = new Latlng(50.364250, -4.141810);

        // Ensure that the null values are caught and are handled correctly
        // As the distances will usually be sorted by shortest, null values should be the max value
        assertEquals(Double.MAX_VALUE, Utils.calcDistance(posOne, posTwo));
    }

    @Test
    void calcDistanceSecondNull() {
        // Two null points
        Latlng posOne = new Latlng(50.375278, -4.139263);
        Latlng posTwo = new Latlng();

        // Ensure that the null values are caught and are handled correctly
        // As the distances will usually be sorted by shortest, null values should be the max value
        assertEquals(Double.MAX_VALUE, Utils.calcDistance(posOne, posTwo));
    }

    @Test
    void calcDistanceAllNull() {
        // Two null points
        Latlng posOne = new Latlng();
        Latlng posTwo = new Latlng();

        // Ensure that the null values are caught and are handled correctly
        // As the distances will usually be sorted by shortest, null values should be the max value
        assertEquals(Double.MAX_VALUE, Utils.calcDistance(posOne, posTwo));
    }

    @Test
    void getRestaurantsByDistanceNoRestaurantsInNeighbourhoodTest() {
        assertThrows(Exception.class, () -> {
            Restaurant[] r = new Restaurant[]{
                    new Restaurant(Neighborhood.Brooklyn, ""),
                    new Restaurant(Neighborhood.Queens, ""),
            };
            ResponseObj res = new ResponseObj();
            res.setRestaurants(r);
            Utils.getRestaurantsByDistance(Neighborhood.Manhattan, res);
        });
    }

    @Test
    void getRestaurantsByDistanceCalculatesDistanceTest() {

    }
}