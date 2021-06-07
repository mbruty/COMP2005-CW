package com.filter_stratergies;

import com.types.Restaurant;

public interface IFilter {
    boolean doCompare(Restaurant restaurant);
}
