package com.codecool.hashmap;

import java.util.Iterator;
import java.util.LinkedList;

// Helper class, holds a key-value pair.
class KeyValue<K, V> {
    K key;
    V value;

    KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }
}


class HashMap<K, V> {

    int bucketSize = 16;

    LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];

    HashMap() {
        for (int i = 0; i < bucketSize; i++) {
            elements[i] = new LinkedList<>();
        }
    }

    void add(K key, V value) {
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        // If the key already exists throw an error.
        Iterator<KeyValue> it = list.iterator();

        while(it.hasNext()){
            if (it.next().key.equals(key)) {
                throw new IllegalArgumentException("This key already exists!");
            }
        }

        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
        KeyValue<K, V> toAdd = new KeyValue<K, V>(key, value);
        list.add(toAdd);
    }

    V getValue(K key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        int position = getHash(key);

        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        LinkedList<KeyValue> list = elements[position];

        Iterator<KeyValue> it = list.iterator();

        while(it.hasNext()){
            KeyValue<K, V> current = it.next();
            if (current.key.equals(key)) {
                return current.value;
            }
        }

        //    If none of the items in the list has this key throw error.
        throw new IllegalArgumentException("None of the items in the list has this key!");
    }

    int getHash(K key) {

        return key.hashCode() % bucketSize;
    }

// + other functions, like clearAll(), delete(),..

}
