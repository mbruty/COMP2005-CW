package unit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.types.Restaurant;
import com.utils.DistancesDsc;
import com.utils.KeyValuePair;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DistancesDscTest {
    private static KeyValuePair<Restaurant, Double> itemOne;
    private static KeyValuePair<Restaurant, Double> itemTwo;

    @BeforeAll
    static void setup() {
        itemOne = new KeyValuePair<>(
                new Restaurant(),
                5d
        );
        itemTwo = new KeyValuePair<>(
                new Restaurant(),
                10d
        );
    }

    @Test
    void doCompareTrue() {
        Assertions.assertTrue(new DistancesDsc().doCompare(itemTwo, itemOne));
    }

    @Test
    void doCompareFalse() {
        assertFalse(new DistancesDsc().doCompare(itemOne, itemTwo));
    }
}