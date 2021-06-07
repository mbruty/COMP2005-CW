package filter_stratergies;

import types.Restaurant;

public interface IFilter {
    boolean doCompare(Restaurant restaurant);
}
