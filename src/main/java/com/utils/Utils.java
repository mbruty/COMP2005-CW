package com.utils;

import com.filter_stratergies.NeighbourhoodFilter;
import com.types.*;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final Hotel[] hotels = new Hotel[]{
            new Hotel("Manhattan", 40.752831, -73.985748),
            new Hotel("Queens", 40.753990, -73.949240),
            new Hotel("Brooklyn", 40.689510, -73.988100),
    };

    public static Hotel[] getHotels() {
        return hotels;
    }

    public static double calcDistance(Latlng source, Latlng destination) {
        if (source.getLat() == null || source.getLng() == null || destination.getLat() == null || destination.getLng() == null) {
            return Double.MAX_VALUE;
        }
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(destination.getLat() - source.getLat());
        double lonDistance = Math.toRadians(destination.getLng() - source.getLng());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(source.getLat())) * Math.cos(Math.toRadians(destination.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public static KeyValuePair<Restaurant, Double>[] getRestaurantsByDistance(Neighborhood neighbourhood, ResponseObj data) throws Exception {
        Hotel hotel = null;
        for (Hotel h :
                hotels) {
            if (h.getNeighborhood().equals(neighbourhood)) {
                hotel = h;
            }
        }
        if (hotel == null) {
            throw new IllegalArgumentException();
        }

        List<Restaurant> restaurantsInNb = data.filter(new NeighbourhoodFilter(neighbourhood));
        List<KeyValuePair<Restaurant, Double>> distances = new ArrayList<>();
        for (Restaurant r : restaurantsInNb) {
            Double distance = calcDistance(hotel.getLatlng(), r.getLatlng());
            distances.add(
                    new KeyValuePair<>(r, distance)
            );
        }
        KeyValuePair<Restaurant, Double>[] sorted = distances.toArray(new KeyValuePair[0]);
        Sort<KeyValuePair<Restaurant, Double>> sort = new Sort<>(sorted);
        sort.changeStrategy(new DistancesDsc());
        sort.doSort();
        return sorted;

    }
}
