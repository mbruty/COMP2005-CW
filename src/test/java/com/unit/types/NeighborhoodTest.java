package com.unit.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.types.Neighborhood;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodTest {

    @Test
    void toValueBrooklyn() {
        Assertions.assertEquals("Brooklyn", Neighborhood.Brooklyn.toValue());
    }

    @Test
    void toValueManhattan() {
        assertEquals("Manhattan", Neighborhood.Manhattan.toValue());
    }

    @Test
    void toValueQueens() {
        assertEquals("Queens", Neighborhood.Queens.toValue());
    }

    @Test
    void forValueBrooklyn() {
        try {
            assertEquals(Neighborhood.Brooklyn, Neighborhood.forValue("Brooklyn"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void forValueManhattan() {
        try {
            assertEquals(Neighborhood.Manhattan, Neighborhood.forValue("Manhattan"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void forValueQueens() {
        try {
            assertEquals(Neighborhood.Queens, Neighborhood.forValue("Queens"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void forValueError() {
        assertThrows(IOException.class, () -> Neighborhood.forValue("Notaneighbourhood"));
    }
}