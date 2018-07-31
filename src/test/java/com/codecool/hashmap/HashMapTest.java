package com.codecool.hashmap;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void testGetHash() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Object obj1 = "Laci";
        Object obj2 = 5;

        int expected1 = obj1.hashCode() % hashMap.bucketSize;
        int expected2 = obj2.hashCode() % hashMap.bucketSize;

        assertEquals(expected1, hashMap.getHash(obj1));
        assertEquals(expected2, hashMap.getHash(obj2));
    }

    @Test
    void testAddThrowsException() {
        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.add("Laci", 2));
    }

    @Test
    void testAdd() {
        HashMap<Object, Object> hashMap = new HashMap<>();

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
        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.add("Laci", 2);
        assertThrows(IllegalArgumentException.class, () -> hashMap.getValue("Barna"));
    }

    @Test
    void testGetValue() {
        HashMap<Object, Object> hashMap = new HashMap<>();

        hashMap.add("Barna", 5);
        hashMap.add("Laci", 2);
        hashMap.add(3, "három");

        assertEquals(2, hashMap.getValue("Laci"));
        assertEquals(5, hashMap.getValue("Barna"));
        assertEquals("három", hashMap.getValue(3));
    }
}