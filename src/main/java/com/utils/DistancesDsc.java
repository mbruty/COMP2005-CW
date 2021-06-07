package utils;

import types.Restaurant;

public class DistancesDsc implements ISort {
    @Override
    public boolean doCompare(Object itemOne, Object itemTwo) {
        KeyValuePair<Restaurant, Double> one = (KeyValuePair<Restaurant, Double>) itemOne;
        KeyValuePair<Restaurant, Double> two = (KeyValuePair<Restaurant, Double>) itemTwo;
        return one.getValue() > two.getValue();
    }
}
