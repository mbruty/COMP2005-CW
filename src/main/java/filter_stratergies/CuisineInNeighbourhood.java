package filter_stratergies;

import types.Restaurant;

public class CuisineInNeighbourhood implements IFilter {
    private final String cuisine;
    private final String neighbourhoodName;
    public CuisineInNeighbourhood(String cuisine, String neighbourhoodName) {
        this.cuisine = cuisine;
        this.neighbourhoodName = neighbourhoodName;
    }
    @Override
    public boolean doCompare(Restaurant restaurant) {
        if(restaurant.getNeighborhood() == null) return false;
        return restaurant.getNeighborhood().toValue().equals(this.neighbourhoodName) && restaurant.getCuisineType().equals(this.cuisine);
    }
}

