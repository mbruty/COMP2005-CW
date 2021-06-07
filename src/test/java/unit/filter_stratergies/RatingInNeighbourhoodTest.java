package unit.filter_stratergies;

import filter_stratergies.IFilter;
import filter_stratergies.RatingInNeighbourhood;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import types.Neighborhood;
import types.Restaurant;
import types.Review;

import static org.junit.jupiter.api.Assertions.*;

class RatingInNeighbourhoodTest {
    private static Restaurant outsideNeighbourhood;
    private static Restaurant nullReviews;
    private static Restaurant noReviews;
    private static Restaurant nullRatings;
    private static Restaurant ratingOfFourPointFive;
    private static Restaurant ratingOfFour;
    private static Restaurant ratingOfThreePointNine;
    private static Restaurant ratingOfThreePointFive;
    private static IFilter filter;
    @BeforeAll
    static void setup() {
        outsideNeighbourhood = new Restaurant(Neighborhood.Queens, "");
        filter = new RatingInNeighbourhood(4, Neighborhood.Manhattan);

        nullReviews = new Restaurant(Neighborhood.Manhattan, "");

        Review[] emptyReviews = new Review[0];
        noReviews = new Restaurant(emptyReviews ,Neighborhood.Manhattan);

        Review[] nullRating = new Review[] {
                new Review()
        };

        nullRatings = new Restaurant(nullRating, Neighborhood.Manhattan);

        Review[] averageOfFourPointFive = new Review[] {
                new Review(5),
                new Review(5),
                new Review(4),
                new Review(4),
        };

        ratingOfFourPointFive = new Restaurant(averageOfFourPointFive, Neighborhood.Manhattan);

        Review[] averageOfFour = new Review[] {
                new Review(5),
                new Review(4),
                new Review(3),
        };

        ratingOfFour = new Restaurant(averageOfFour, Neighborhood.Manhattan);

        Review[] averageOfThreePointNine = new Review[] {
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(4),
                new Review(3),
        };

        ratingOfThreePointNine = new Restaurant(averageOfThreePointNine, Neighborhood.Manhattan);

        Review[] averageOfThreePointFive = new Review[] {
                new Review(4),
                new Review(4),
                new Review(3),
                new Review(3),
        };

        ratingOfThreePointFive = new Restaurant(averageOfThreePointFive, Neighborhood.Manhattan);
    }

    @Test
    void outsideNeighbourhood() { assertFalse(filter.doCompare(outsideNeighbourhood)); }

    @Test
    void nullReviews() { assertFalse(filter.doCompare(nullReviews)); }

    @Test
    void noReviews() { assertFalse(filter.doCompare(noReviews)); }

    @Test
    void nullRatings() { assertFalse(filter.doCompare(nullRatings)); }

    @Test
    void largerThanMinimumAverageReview() { assertTrue(filter.doCompare(ratingOfFourPointFive)); }

    @Test
    void exactlyMinimumAverageReview() { assertTrue(filter.doCompare(ratingOfFour)); }

    @Test
    void justBelowMinimumAverageReview() { assertFalse(filter.doCompare(ratingOfThreePointNine)); }

    @Test
    void belowMinimumAverageReview() { assertFalse(filter.doCompare(ratingOfThreePointFive)); }
}