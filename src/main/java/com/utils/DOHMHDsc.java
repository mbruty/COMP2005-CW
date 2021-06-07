package com.utils;

import com.types.Restaurant;

public class DOHMHDsc implements ISort {
    @Override
    public boolean doCompare(Object itemOne, Object itemTwo) {
        Restaurant one = (Restaurant) itemOne;
        Restaurant two = (Restaurant) itemTwo;

        // Move any -1 values to the bottom
        // -1 values are values where the score is ""
        if (one.getDohmhInspectionScore() == -1) {
            return true;
        }
        if (two.getDohmhInspectionScore() == -1) {
            return false;
        }
        return one.getDohmhInspectionScore() > two.getDohmhInspectionScore();
    }
}
