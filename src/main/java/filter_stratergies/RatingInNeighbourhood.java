package filter_stratergies;

import types.Restaurant;
import types.Review;

public class RatingInNeighbourhood implements IFilter {
    private final float minRating;
    private final String neighbourhood;

    public RatingInNeighbourhood(float rating, String neighbourhood) {
        this.minRating = rating;
        this.neighbourhood = neighbourhood;
    }
    @Override
    public boolean doCompare(Restaurant restaurant) {
        if(!restaurant.getNeighborhood().toValue().equals(this.neighbourhood)) {
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
