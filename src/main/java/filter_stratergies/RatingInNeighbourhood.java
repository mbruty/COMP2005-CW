package filter_stratergies;

import types.Neighborhood;
import types.Restaurant;
import types.Review;

public class RatingInNeighbourhood implements IFilter {
    private final float minRating;
    private final Neighborhood neighbourhood;

    public RatingInNeighbourhood(float rating, Neighborhood neighbourhood) {
        this.minRating = rating;
        this.neighbourhood = neighbourhood;
    }
    @Override
    public boolean doCompare(Restaurant restaurant) {
        if(!restaurant.getNeighborhood().equals(this.neighbourhood)) {
            return false;
        }
        if(restaurant.getReviews() == null || restaurant.getReviews().length < 1) {
            return false;
        }
        int accumulator = 0;
        for (Review r:
             restaurant.getReviews()) {
            accumulator += r.getRating();
        }
        float average = (float)accumulator / restaurant.getReviews().length;
        return  average >= this.minRating;
    }
}
