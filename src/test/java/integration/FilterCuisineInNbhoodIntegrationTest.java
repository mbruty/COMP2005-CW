package integration;

import filter_stratergies.CuisineInNeighbourhood;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.ResponseObj;
import types.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterCuisineInNbhoodIntegrationTest {

    private static Restaurant manhattanAsian;
    private static Restaurant manhattanAmerican;
    private static Restaurant queenAsian;
    private static Restaurant queenAmerican;
    private static Restaurant brooklynAsian;
    private static Restaurant brooklynAmerican;
    private ResponseObj res;

    @BeforeAll
    static void setupRestaurants() {
        // Create some dummy restaurants
        manhattanAsian = new Restaurant();
        manhattanAsian.setNeighborhood(Neighborhood.Manhattan);
        manhattanAsian.setCuisineType("Asian");

        manhattanAmerican = new Restaurant();
        manhattanAmerican.setNeighborhood(Neighborhood.Manhattan);
        manhattanAmerican.setCuisineType("American");

        queenAsian = new Restaurant();
        queenAsian.setNeighborhood(Neighborhood.Queens);
        queenAsian.setCuisineType("Asian");

        queenAmerican = new Restaurant();
        queenAmerican.setNeighborhood(Neighborhood.Queens);
        queenAmerican.setCuisineType("American");

        brooklynAsian = new Restaurant();
        brooklynAsian.setNeighborhood(Neighborhood.Brooklyn);
        brooklynAsian.setCuisineType("Asian");

        brooklynAmerican = new Restaurant();
        brooklynAmerican.setNeighborhood(Neighborhood.Brooklyn);
        brooklynAmerican.setCuisineType("American");
    }

    @BeforeEach
    void setup() {
        res = new ResponseObj();
    }

    @Test
    void cuisineInNbhoodFilterTest() {
        res.setRestaurants(new Restaurant[]{
                manhattanAsian, manhattanAmerican, queenAsian, queenAmerican, brooklynAsian, brooklynAmerican
        });

        List<Restaurant> result = new ArrayList<>();
        try {
            result = res.filter(new CuisineInNeighbourhood("Asian", Neighborhood.Manhattan));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        // Test that the result array is not empty
        assertNotEquals(0, result.size());

        for (Restaurant r :
                result) {
            assertEquals("Asian", r.getCuisineType());
            assertEquals(Neighborhood.Manhattan, r.getNeighborhood());
        }
    }

    @Test
    void cuisineInNbhoodFilterNbhoodNotFoundTest() {
        res.setRestaurants(new Restaurant[]{manhattanAsian, manhattanAmerican, queenAsian, queenAmerican});

        assertThrows(Exception.class, () -> res.filter(new CuisineInNeighbourhood("Asian", Neighborhood.Brooklyn)));
    }

    @Test
    void cuisineInNbhoodFilterCuisineNotFoundTest() {
        res.setRestaurants(new Restaurant[]{
                manhattanAsian, manhattanAmerican, queenAsian, queenAmerican, brooklynAsian, brooklynAmerican
        });

        assertThrows(Exception.class, () -> res.filter(new CuisineInNeighbourhood("NotaCuisine", Neighborhood.Brooklyn)));
    }
}
