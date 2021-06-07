package com.types;

import com.filter_stratergies.IFilter;
import com.utils.ISort;
import com.utils.Sort;

import java.util.ArrayList;
import java.util.List;

public class ResponseObj {
    private Restaurant[] restaurants;

    public ResponseObj(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    // Empty constructor for Deserialization
    public ResponseObj() {

    }

    public Restaurant[] getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant[] value) {
        this.restaurants = value;
    }

    public List<Restaurant> filter(IFilter filterFn) throws Exception {
        List<Restaurant> filtered = new ArrayList<>();
        for (Restaurant restaurant :
                this.restaurants) {
            if (filterFn.doCompare(restaurant)) {
                filtered.add(restaurant);
            }
        }
        if (filtered.size() == 0) {
            throw new Exception("Don't exist, bruh");
        }
        return filtered;
    }

    public Restaurant[] sort(ISort sortStrategy) {
        // Throw an error if there is no array
        if (this.restaurants == null || this.restaurants.length == 0) {
            throw new IllegalArgumentException("There are no elements to sort on");
        }
        // Clone the original array
        // The sort implementation does an in place sort
        Restaurant[] copy = this.cloneRestaurants();
        Sort<Restaurant> sort = new Sort<>(copy);
        sort.changeStrategy(sortStrategy);
        try {
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
