package types;

import filter_stratergies.IFilter;
import utils.ISort;
import utils.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseObj {
    private Restaurant[] restaurants;

    public ResponseObj(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    // Empty constructor for Deserialization
    public ResponseObj() {

    }

    public Restaurant[] getRestaurants() { return restaurants; }
    public void setRestaurants(Restaurant[] value) { this.restaurants = value; }

    public List<Restaurant> filter(IFilter filterFn) throws Exception {
        List<Restaurant> filtered = new ArrayList<>();
        for (Restaurant restaurant:
             this.restaurants) {
            if(filterFn.doCompare(restaurant)) {
                filtered.add(restaurant);
            }
        }
        if(filtered.size() == 0) {
            throw new Exception("Don't exist, bruh");
        }
        return filtered;
    }

    public Restaurant[] sort(ISort sortStrategy) {
        // Clone the original array
        // The sort implementation does an in place sort
        Restaurant [] copy = this.cloneRestaurants();
        Sort<Restaurant> sort = new Sort<>(copy);
        sort.changeStrategy(sortStrategy);
        try{
            sort.doSort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copy;
    }

    public Restaurant[] cloneRestaurants() {
        Restaurant[] copy = new Restaurant[this.restaurants.length];
        try {
            for (int i = 0; i < this.restaurants.length; i++) {
                copy[i] = this.restaurants[i].clone();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copy;
    }
}
