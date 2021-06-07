package com.filter_stratergies;

import com.types.Neighborhood;
import com.types.Restaurant;

public class CuisineInNeighbourhood implements IFilter {
    private final String cuisine;
    private final Neighborhood neighbourhood;

    public CuisineInNeighbourhood(String cuisine, Neighborhood neighbourhood) {
        this.cuisine = cuisine;
        this.neighbourhood = neighbourhood;
    }

    @Override
    public boolean doCompare(Restaurant restaurant) {
        if (restaurant.getNeighborhood() == null) return false;
        return restaurant.getNeighborhood().equals(this.neighbourhood) && restaurant.getCuisineType().equals(this.cuisine);
    }
}

