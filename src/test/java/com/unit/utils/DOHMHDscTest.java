package com.unit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.types.Restaurant;
import com.utils.DOHMHDsc;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DOHMHDscTest {
    private static Restaurant itemOne;
    private static Restaurant itemTwo;

    @BeforeAll
    static void setup() {
        itemOne = new Restaurant();
        itemTwo = new Restaurant();

        itemOne.setDohmhInspectionScore("1");
        itemTwo.setDohmhInspectionScore("5");
    }

    @Test
    void doCompareTrue() {
        Assertions.assertTrue(new DOHMHDsc().doCompare(itemTwo, itemOne));
    }

    @Test
    void doCompareFalse() {
        assertFalse(new DOHMHDsc().doCompare(itemOne, itemTwo));
    }
}