package com.unit.types;

import org.junit.jupiter.api.Test;
import com.types.Restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    // Some times the api will throw back an empty dohmh score, so ensure it's handled for
    @Test
    void setDohmhInspectionScore() {
        Restaurant r = new Restaurant();
        r.setDohmhInspectionScore("");
        assertEquals(-1, r.getDohmhInspectionScore());
    }
}