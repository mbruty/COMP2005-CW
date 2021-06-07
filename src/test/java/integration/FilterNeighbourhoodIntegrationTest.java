package integration;

import filter_stratergies.NeighbourhoodFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.ResponseObj;
import types.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterNeighbourhoodIntegrationTest {

    private static Restaurant manhattan;
    private static Restaurant brooklyn;
    private static Restaurant queens;
    private ResponseObj res;

    @BeforeAll
    static void setup() {
        manhattan = new Restaurant();
        manhattan.setNeighborhood(Neighborhood.Manhattan);

        brooklyn = new Restaurant();
        brooklyn.setNeighborhood(Neighborhood.Brooklyn);

        queens = new Restaurant();
        queens.setNeighborhood(Neighborhood.Queens);
    }

    @BeforeEach
    void createResponseObject() {
        res = new ResponseObj();
    }

    @Test
    void filterNeighbourhoodTest() {
        res.setRestaurants(new Restaurant[]{manhattan, brooklyn, queens});
        List<Restaurant> result = new ArrayList<>();
        try {
            result = res.filter(new NeighbourhoodFilter(Neighborhood.Brooklyn));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(1, result.size());
        assertEquals(brooklyn, result.get(0));
    }

    @Test
    void filterNeighbourhoodNotFoundTest() {
        res.setRestaurants(new Restaurant[]{manhattan, queens});
        List<Restaurant> result = new ArrayList<>();
        assertThrows(Exception.class, () -> res.filter(new NeighbourhoodFilter(Neighborhood.Brooklyn)));
    }

    @Test
    void filterNeighbourhoodNoRestaurants() {
        assertThrows(Exception.class, () -> res.filter(new NeighbourhoodFilter(Neighborhood.Brooklyn)));
    }
}
