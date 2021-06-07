package filter_stratergies;

import types.Neighborhood;
import types.Restaurant;

public class NeighbourhoodFilter implements IFilter {

    private final Neighborhood neighbourhood;

    public NeighbourhoodFilter(Neighborhood neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
    @Override
    public boolean doCompare(Restaurant restaurant) {
        return restaurant.getNeighborhood().equals(this.neighbourhood);
    }
}
