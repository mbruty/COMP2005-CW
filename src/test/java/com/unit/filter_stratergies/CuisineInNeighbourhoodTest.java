package com.unit.filter_stratergies;

import com.filter_stratergies.CuisineInNeighbourhood;
import com.filter_stratergies.IFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.types.Neighborhood;
import com.types.Restaurant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CuisineInNeighbourhoodTest {
    private static Restaurant wrongCuisine;
    private static Restaurant wrongNbhood;
    private static Restaurant noCuisine;
    private static Restaurant emptyRestaurant;
    private static Restaurant match;
    private static IFilter filter;

    @BeforeAll
    static void setUp() {
        wrongCuisine = new Restaurant(Neighborhood.Manhattan, "Italian");
        wrongNbhood = new Restaurant(Neighborhood.Queens, "Chinese");
        noCuisine = new Restaurant(Neighborhood.Manhattan, "");
        emptyRestaurant = new Restaurant();

        match = new Restaurant(Neighborhood.Manhattan, "Chinese");

        filter = new CuisineInNeighbourhood("Chinese", Neighborhood.Manhattan);
    }

    @Test
    void compareWrongCuisine() {
        assertFalse(filter.doCompare(wrongCuisine));
    }

    @Test
    void compareWrongNeighbourhood() {
        assertFalse(filter.doCompare(wrongNbhood));
    }

    @Test
    void compareEmptyCuisine() {
        assertFalse(filter.doCompare(noCuisine));
    }

    @Test
    void compareRestaurantWithNoData() {
        assertFalse(filter.doCompare(emptyRestaurant));
    }

    @Test
    void compareSameNeighbourhoodAndCuisine() {
        assertTrue(filter.doCompare(match));
    }
}