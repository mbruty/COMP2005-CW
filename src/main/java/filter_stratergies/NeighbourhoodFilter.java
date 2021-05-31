package filter_stratergies;

import types.Restaurant;

public class NeighbourhoodFilter implements IFilter {

    private final String neighbourhood;

    public NeighbourhoodFilter(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }
    @Override
    public boolean doCompare(Restaurant restaurant) {
        return restaurant.getNeighborhood().toValue().equals(this.neighbourhood);
    }
}
