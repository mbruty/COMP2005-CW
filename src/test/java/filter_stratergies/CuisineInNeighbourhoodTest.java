package filter_stratergies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.Restaurant;

import static org.junit.jupiter.api.Assertions.*;

class CuisineInNeighbourhoodTest {
    private Restaurant wrongCuisine;
    private Restaurant wrongNbhood;
    private Restaurant noCuisine;
    private Restaurant nullRestaurant;
    private Restaurant match;
    private IFilter filter;
    @BeforeEach
    void setUp() {
        try {
            this.wrongCuisine = new Restaurant(Neighborhood.forValue("Manhattan"), "Italian");
            this.wrongNbhood = new Restaurant(Neighborhood.forValue("Queens"), "Chinese");
            this.noCuisine = new Restaurant(Neighborhood.forValue("Manhattan"), "");
            this.nullRestaurant = new Restaurant();

            this.match = new Restaurant(Neighborhood.forValue("Manhattan"), "Chinese");

            this.filter = new CuisineInNeighbourhood("Chinese", "Manhattan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void doCompare() {
        assertFalse(filter.doCompare(wrongCuisine));
        assertFalse(filter.doCompare(wrongNbhood));
        assertFalse(filter.doCompare(noCuisine));
        assertFalse(filter.doCompare(nullRestaurant));

        assertTrue(filter.doCompare(match));
    }
}