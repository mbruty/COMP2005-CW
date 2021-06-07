package com.unit.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.utils.KeyValuePair;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeyValuePairTest {

    private static KeyValuePair<String, String> kv;

    @BeforeAll
    static void setup() {
        kv = new KeyValuePair<>("Hello", "World");
    }

    @Test
    void getKeyTest() {
        assertEquals("Hello", kv.getKey());
    }

    @Test
    void getValueTest() {
        assertEquals("World", kv.getValue());
    }
}