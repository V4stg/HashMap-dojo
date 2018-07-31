package com.codecool.hashmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

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

        int expected1 = obj1.hashCode() % hashMap.bucketSize;
        int expected2 = obj2.hashCode() % hashMap.bucketSize;

        assertEquals(expected1, hashMap.getHash(obj1));
        assertEquals(expected2, hashMap.getHash(obj2));
    }

    @Test
    void testAddThrowsException() {

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.add("Laci", 2));
    }

    @Test
    void testAdd() {

        hashMap.add("Laci", 2);

        KeyValue stored1 = null;

        for (LinkedList<KeyValue> element : hashMap.elements) {
            if (!element.isEmpty()) {
                stored1 = element.pop();
            }
        }

        assert stored1 != null;
        assertEquals("Laci", stored1.key);
        assertEquals(2, stored1.value);
    }

    @Test
    void testGetValueThrowException() {

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.getValue("Barna"));
    }

    @Test
    void testGetValue() {

        hashMap.add("Barna", 5);
        hashMap.add("Laci", 2);
        hashMap.add(3, "három");

        assertEquals(2, hashMap.getValue("Laci"));
        assertEquals(5, hashMap.getValue("Barna"));
        assertEquals("három", hashMap.getValue(3));
    }

    @Test
    void testGetHashHandlingNegativeIntegers() {

        for (int i = 0; i < 5000; i++) {
            hashMap.add(i + "hello", i);
        }
    }
}