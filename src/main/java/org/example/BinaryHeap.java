package org.example;

public interface BinaryHeap<V> {

    void add(V value);

    void add(V... values);

    V remove();

    int size();
}
