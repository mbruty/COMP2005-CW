package com.functional;

import com.DataAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.filter_stratergies.CuisineInNeighbourhood;
import com.types.Neighborhood;
import com.types.Restaurant;
import org.junit.jupiter.api.Test;
import com.types.ResponseObj;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Access  the  API  and  return  a  list  of  all  restaurants  of  a  specific  type  of  cuisine  in  a  specific
//neighbourhood.
public class AccessAPIAndFilterByNeighbourhoodAndCuisineTest {
    @Test
    void main() {
        String json = DataAccess.get();
        ResponseObj data;

        try {
            data = DataAccess.deserialize(json);
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
            return;
        }
        List<Restaurant> result = new ArrayList<>();
        try{
            result = data.filter(new CuisineInNeighbourhood("Asian", Neighborhood.Manhattan));
        } catch (Exception e){
            fail(e.getMessage());
        }

        assertNotEquals(0, result.size());

        // Assert that every restaurant in the result is expected from the filter
        for (Restaurant r :
                result) {
            assertEquals(Neighborhood.Manhattan, r.getNeighborhood());
            assertEquals("Asian", r.getCuisineType());
        }

        // Assert that every item in the original dataset that is not in the result does not match
        // the filter
        for (Restaurant r :
                data.getRestaurants()) {
            if(!result.contains(r)){
                boolean restaurantIsNotInNeighbourhoodOrIsNotCuisine = !r.getCuisineType().equals("Asian") || r.getNeighborhood() != Neighborhood.Manhattan;
                assertTrue(restaurantIsNotInNeighbourhoodOrIsNotCuisine);
            }
        }

    }
}
