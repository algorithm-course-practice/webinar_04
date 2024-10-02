package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyBinaryHeapTest {

    public static final int CAPACITY = 1 << 6;
    MyBinaryHeap<Integer> heap = new MyBinaryHeap<>(CAPACITY);

    @Test
    void add() {
        for (int i = 0; i < 10; i++) {
            heap.add(i);
            assertEquals(i+1, heap.size());
        }
        assertTrees(
                "               [0]9          \n" +
                "         ┌──────┴──────┐     \n" +
                "        [1]8          [2]5   \n" +
                "    ┌────┴────┐     ┌──┴──┐  \n" +
                "   [3]6      [4]7  [5]1  [6]4\n" +
                " ┌──┴──┐     ┌┘              \n" +
                "[7]0  [8]3  [9]2             \n",
                heap.print());
        for(int i = 9; i >= 0; i--) {
            assertEquals(i, heap.remove());
            assertEquals(i, heap.size());
        }
    }

    @Test
    void insertArray() {
        heap.isDbg = true;
        heap.add(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertTrees(
                "               [0]9          \n" +
                "         ┌──────┴──────┐     \n" +
                "        [1]8          [2]6   \n" +
                "    ┌────┴────┐     ┌──┴──┐  \n" +
                "   [3]7      [4]4  [5]5  [6]2\n" +
                " ┌──┴──┐     ┌┘              \n" +
                "[7]0  [8]3  [9]1             \n",
                heap.print()
        );
    }

    private void assertTrees(String print, String actual) {
        assertEquals(print.replaceAll("\r\n", "\n"), actual.replaceAll("\r\n", "\n"));
    }
}