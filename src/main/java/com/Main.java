package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.filter_stratergies.CuisineInNeighbourhood;
import com.filter_stratergies.DayAndHour;
import com.filter_stratergies.RatingInNeighbourhood;
import com.types.Latlng;
import com.types.Neighborhood;
import com.types.ResponseObj;
import com.types.Restaurant;
import com.utils.DOHMHDsc;
import com.utils.KeyValuePair;
import com.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String json = DataAccess.get();
        ResponseObj data;
        try {
            data = DataAccess.deserialize(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        try {
            List<Restaurant> filtered = data.filter(new CuisineInNeighbourhood("American", Neighborhood.Manhattan));
            for (Restaurant r :
                    filtered) {
                System.out.println(r.getName());
            }

            List<Restaurant> filtered2 = data.filter(new DayAndHour("Saturday", "12", "30"));
            for (Restaurant r :
                    filtered2) {
                System.out.println(r.getName());
            }

            List<Restaurant> filtered3 = data.filter(new RatingInNeighbourhood(4, Neighborhood.Manhattan));
            for (Restaurant r :
                    filtered3) {
                System.out.println(r.getName() + "\t" + Arrays.toString(r.getReviews()));
            }

            Restaurant[] sorted = data.sort(new DOHMHDsc());
            for (Restaurant r :
                    sorted) {
                System.out.println(r.getName() + "\t" + r.getDohmhInspectionScore());
            }

            System.out.println(Utils.calcDistance(new Latlng(50.375148, -4.139186), new Latlng(50.396614, -4.036022)));

            KeyValuePair<Restaurant, Double>[] distances = Utils.getRestaurantsByDistance(Neighborhood.Brooklyn, data);
            for (KeyValuePair<Restaurant, Double> kv :
                    distances) {
                System.out.println(kv.getKey().getName() + "\t" + kv.getValue().toString());
            }

        } catch (Exception e) {
            System.out.println("Oopsie Doopsie");
        }

    }
}
