package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.types.Neighborhood;
import com.types.ResponseObj;
import com.types.Restaurant;
import org.junit.jupiter.api.Test;
import org.json.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    @Test
    void getIsValidJson() {
        String json = DataAccess.get();
        assertDoesNotThrow(() -> new JSONObject(json));
    }

    @Test
    void deserialize() {
        String json = """
                {
                  "restaurants": [{
                    "id": 1,
                    "name": "Mission Chinese Food",
                    "DOHMH_inspection_score": "13",
                    "neighborhood": "Manhattan",
                    "photograph": "1.jpg",
                    "address": "171 E Broadway, New York, NY 10002",
                    "latlng": {
                      "lat": 40.713829,
                      "lng": -73.989667
                    },
                    "cuisine_type": "Asian",
                    "operating_hours": {
                      "Monday": "5:30 pm - 11:00 pm",
                      "Tuesday": "5:30 pm - 12:00 am",
                      "Wednesday": "5:30 pm - 12:00 am",
                      "Thursday": "5:30 pm - 12:00 am",
                      "Friday": "5:30 pm - 12:00 am",
                      "Saturday": "12:00 pm - 4:00 pm, 5:30 pm - 12:00 am",
                      "Sunday": "12:00 pm - 4:00 pm, 5:30 pm - 11:00 pm"
                    },
                    "reviews": [{
                        "name": "Steve",
                        "date": "October 26, 2016",
                        "rating": 4,
                        "comments": "Mission Chinese Food has grown up from its scrappy Orchard Street days into a big, two story restaurant equipped with a pizza oven, a prime rib cart, and a much broader menu. Yes, it still has all the hits — the kung pao pastrami, the thrice cooked bacon —but chef/proprietor Danny Bowien and executive chef Angela Dimayuga have also added a raw bar, two generous family-style set menus, and showstoppers like duck baked in clay. And you can still get a lot of food without breaking the bank."
                      }]
                  }]
                  }
                  """;
        ResponseObj res;
        try {
             res = DataAccess.deserialize(json);
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
            return;
        }

        assertEquals(1, res.getRestaurants().length);

        Restaurant r = res.getRestaurants()[0];

        // Assert all fields are equal
        assertEquals(1, r.getID());
        assertEquals("Mission Chinese Food", r.getName());
        assertEquals(13, r.getDohmhInspectionScore());
        assertEquals(Neighborhood.Manhattan, r.getNeighborhood());
        assertEquals("1.jpg", r.getPhotograph());
        assertEquals("171 E Broadway, New York, NY 10002", r.getAddress());
        assertEquals(40.713829, r.getLatlng().getLat());
        assertEquals(-73.989667, r.getLatlng().getLng());
        assertEquals("Asian", r.getCuisineType());
        assertEquals("[17:30 - 23:0]", Arrays.toString(r.getOperatingHours().getMonday()));
        assertEquals("[17:30 - 24:0]", Arrays.toString(r.getOperatingHours().getTuesday()));
        assertEquals("[17:30 - 24:0]", Arrays.toString(r.getOperatingHours().getWednesday()));
        assertEquals("[17:30 - 24:0]", Arrays.toString(r.getOperatingHours().getThursday()));
        assertEquals("[17:30 - 24:0]", Arrays.toString(r.getOperatingHours().getFriday()));
        assertEquals("[12:0 - 16:0, 17:30 - 24:0]", Arrays.toString(r.getOperatingHours().getSaturday()));
        assertEquals("[12:0 - 16:0, 17:30 - 23:0]", Arrays.toString(r.getOperatingHours().getSunday()));
        assertEquals("Steve", r.getReviews()[0].getName());
        assertEquals("October 26, 2016", r.getReviews()[0].getDate());
        assertEquals(4, r.getReviews()[0].getRating());
        assertEquals("Mission Chinese Food has grown up from its scrappy Orchard Street days into a big, two story restaurant equipped with a pizza oven, a prime rib cart, and a much broader menu. Yes, it still has all the hits — the kung pao pastrami, the thrice cooked bacon —but chef/proprietor Danny Bowien and executive chef Angela Dimayuga have also added a raw bar, two generous family-style set menus, and showstoppers like duck baked in clay. And you can still get a lot of food without breaking the bank.",
                r.getReviews()[0].getComments());
    }
}