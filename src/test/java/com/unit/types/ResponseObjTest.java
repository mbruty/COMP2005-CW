package com.unit.types;

import com.filter_stratergies.IFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.types.ResponseObj;
import com.types.Restaurant;
import com.utils.ISort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseObjTest {

    private static Restaurant[] restaurants;
    private ResponseObj res;

    @BeforeAll
    static void setup() {
        restaurants = new Restaurant[5];
        for (int i = 0; i < 5; i++) {
            Restaurant r = new Restaurant();
            r.setID(i);
            restaurants[i] = r;
        }
    }

    @BeforeEach
    void setupResponseObject() {
        res = new ResponseObj(restaurants);
    }

    @Test
    void filterDoesNotAlterOriginalArray() {
        try {
            // Filter everything out
            res.filter(new MockFilterFalse());
        }
        // This should throw an error, but we're not testing that here
        catch (Exception ignored) {
        }
        assertEquals(5, restaurants.length);

    }

    @Test
    void filterAll() {
        try {
            // Filter all true
            List<Restaurant> result = res.filter(new MockFilterTrue());

            // No objects should be removed, so the length should be the same
            assertEquals(restaurants.length, result.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void filterNone() {
        // Filter with all false
        // Should throw an error if no items are found
        assertThrows(Exception.class, () -> res.filter(new MockFilterFalse()));
    }

    @Test
    void filterWithNoArray() {
        ResponseObj r = new ResponseObj();
        assertThrows(Exception.class, () -> r.filter(new MockFilterTrue()));
    }

    @Test
        // Testing that the filter will return the correct results from a simple filter
    void filterEvenIds() {
        try {
            List<Restaurant> result = res.filter(new MockFilterEvenId());
            for (Restaurant r :
                    result) {
                assertEquals(r.getID() % 2, 0);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void sort() {
        Restaurant[] result = res.sort(new MockSortIdDSC());
        for (int i = 0; i < result.length - 1; i++) {
            assertTrue(result[i].getID() > result[i + 1].getID());
        }
    }

    @Test
    void sortDoesNotChangeOriginalArray() {
        res.sort(new MockSortIdDSC());
        Restaurant[] original = res.getRestaurants();
        // When the original array is created, the ids will be sorted in ascending order
        // So if the original array is sorted in descending order it should fail
        for (int i = 0; i < original.length - 1; i++) {
            assertTrue(original[i].getID() < original[i + 1].getID());
        }
    }

    @Test
    void sortWithNoArray() {
        ResponseObj r = new ResponseObj();
        assertThrows(IllegalArgumentException.class, () -> r.sort(new MockSortIdDSC()));
    }

    @Test
    void cloneRestaurants() {
    }

    private class MockFilterTrue implements IFilter {
        @Override
        public boolean doCompare(Restaurant restaurant) {
            return true;
        }
    }

    private class MockFilterFalse implements IFilter {
        @Override
        public boolean doCompare(Restaurant restaurant) {
            return false;
        }
    }

    private class MockFilterEvenId implements IFilter {
        @Override
        public boolean doCompare(Restaurant restaurant) {
            // mock filter that will sometimes be true, sometimes be false.
            // Even elements will return true, odd will return false
            return restaurant.getID() % 2 == 0;
        }
    }

    private class MockSortIdDSC implements ISort {
        @Override
        public boolean doCompare(Object itemOne, Object itemTwo) {
            Restaurant one = (Restaurant) itemOne;
            Restaurant two = (Restaurant) itemTwo;
            return one.getID() < two.getID();
        }
    }
}