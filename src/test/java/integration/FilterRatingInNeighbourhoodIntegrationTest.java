package integration;

import filter_stratergies.RatingInNeighbourhood;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.ResponseObj;
import types.Restaurant;
import types.Review;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterRatingInNeighbourhoodIntegrationTest {

    private static Restaurant correctNeighbourhoodAboveRating;
    private static Restaurant correctNeighbourhoodBelowRating;
    private static Restaurant wrongNeighbourhoodAboveRating;
    private static Restaurant wrongNeighbourhoodBelowRating;
    private ResponseObj res;

    @BeforeAll
    static void setup() {
        Review one;

        correctNeighbourhoodAboveRating = new Restaurant();
        one = new Review();
        one.setRating(5);
        correctNeighbourhoodAboveRating.setReviews(new Review[]{one});
        correctNeighbourhoodAboveRating.setNeighborhood(Neighborhood.Manhattan);

        correctNeighbourhoodBelowRating = new Restaurant();
        one = new Review();
        one.setRating(1);
        correctNeighbourhoodBelowRating.setReviews(new Review[]{one});
        correctNeighbourhoodBelowRating.setNeighborhood(Neighborhood.Manhattan);

        wrongNeighbourhoodAboveRating = new Restaurant();
        one = new Review();
        one.setRating(5);
        wrongNeighbourhoodAboveRating.setReviews(new Review[]{one});
        wrongNeighbourhoodAboveRating.setNeighborhood(Neighborhood.Queens);

        wrongNeighbourhoodBelowRating = new Restaurant();
        one = new Review();
        one.setRating(1);
        wrongNeighbourhoodBelowRating.setReviews(new Review[]{one});
        wrongNeighbourhoodBelowRating.setNeighborhood(Neighborhood.Queens);
    }

    @BeforeEach
    void createResponseObject() {
        res = new ResponseObj();
    }

    @Test
    void filterNeighbourhoodAboveRating() {
        res.setRestaurants(new Restaurant[]{correctNeighbourhoodAboveRating, correctNeighbourhoodBelowRating, wrongNeighbourhoodAboveRating, wrongNeighbourhoodBelowRating});
        List<Restaurant> result = new ArrayList<>();
        try{
            result = res.filter(new RatingInNeighbourhood(5f, Neighborhood.Manhattan));
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(1, result.size());
        assertEquals(correctNeighbourhoodAboveRating, result.get(0));
    }

    @Test
    void filterNeighbourhoodRatingTooHigh() {
        res.setRestaurants(new Restaurant[]{correctNeighbourhoodAboveRating, correctNeighbourhoodBelowRating, wrongNeighbourhoodAboveRating, wrongNeighbourhoodBelowRating});
        assertThrows(Exception.class, () -> res.filter(new RatingInNeighbourhood(6f, Neighborhood.Manhattan)));
    }

    @Test
    void filterNeighbourhoodNeighbourhoodNotFound() {
        res.setRestaurants(new Restaurant[]{correctNeighbourhoodAboveRating, correctNeighbourhoodBelowRating, wrongNeighbourhoodAboveRating, wrongNeighbourhoodBelowRating});
        assertThrows(Exception.class, () -> res.filter(new RatingInNeighbourhood(5f, Neighborhood.Brooklyn)));
    }

    @Test
    void filterNeighbourhoodNoRestaurants() {
        assertThrows(Exception.class, () -> res.filter(new RatingInNeighbourhood(6f, Neighborhood.Manhattan)));
    }
}
