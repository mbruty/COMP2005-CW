package filter_stratergies;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.Restaurant;

import static org.junit.jupiter.api.Assertions.*;

class NeighbourhoodFilterTest {
    private static Restaurant manhattan;
    private static Restaurant queens;
    private static IFilter filter;
    @BeforeAll
    static void setUp() {
        manhattan = new Restaurant(Neighborhood.Manhattan, "");
        queens = new Restaurant(Neighborhood.Queens, "");
        filter = new NeighbourhoodFilter(Neighborhood.Manhattan);
    }

    @Test
    void compareSameNeighbourhood() {
        assertTrue(filter.doCompare(manhattan));
    }

    @Test
    void compareDifferentNeighbourhood() {
        assertFalse(filter.doCompare(queens));
    }
}