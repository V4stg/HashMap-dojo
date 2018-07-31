package com.codecool.hashmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private HashMap<Object, Object> hashMap;

    @BeforeEach
    void setUp() {
        hashMap = new HashMap<>();
    }

    @Test
    void testGetHash() {

        Object obj1 = "Laci";
        Object obj2 = 5;

        assertEquals(getExpected(obj1), hashMap.getHash(obj1));
        assertEquals(getExpected(obj2), hashMap.getHash(obj2));
    }

    private static int getExpected(Object obj1) {
        return Math.abs(obj1.hashCode() % 16);
    }

    @Test
    void testGetHashHandlingNegativeIntegers() {

        for (int i = 0; i < 5000; i++) {
            hashMap.add(i + "hello", i);
        }
    }

    @Test
    void testAddThrowsException() {

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.add("Laci", 2));
    }

    @Test
    void testAdd() {

        hashMap.add("Barna", 5);
        hashMap.add("Laci", 2);
        hashMap.add(3, "három");
    }

    @Test
    void testGetValueThrowException() {

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.getValue("Barna"));
    }

    @Test
    void testGetValue() {

        testAdd();

        assertEquals(2, hashMap.getValue("Laci"));
        assertEquals(5, hashMap.getValue("Barna"));
        assertEquals("három", hashMap.getValue(3));
    }

    @Test
    void testDelete() {

        testAdd();

        hashMap.delete("Laci");
        hashMap.delete("Barna");
    }

    @Test
    void testDeleteThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> hashMap.delete("Laci"));

        hashMap.add(2, "kettő");

        assertThrows(IllegalArgumentException.class, () -> hashMap.delete(1));
    }
}