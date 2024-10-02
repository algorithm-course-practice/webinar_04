package org.example;

import tech.vanyo.treePrinter.TreePrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyBinaryHeap<V extends Comparable> implements BinaryHeap<V> {

    private V heap[];

    private int size = 0;

    public boolean isDbg = false;

    public MyBinaryHeap(int capacity) {
        heap = (V[]) new Comparable[capacity];
    }

    /**
     * Добавляем в конец кучи и проверяем родителя
     * @param value
     */
    @Override
    public void add(V value) {
        heap[size++] = value;
        checkRules(size - 1);
    }

    /**
     * Добавляем все элементы в кучу, без проверок.
     * От середины до начала кучи запускаем генерацию кучи
     * @param values
     */
    public void add(V... values) {
        if (values.length + size >= heap.length) {
            throw new IllegalArgumentException("values not fit in heap");
        }
        for (V value : values) {
            heap[size++] = value;
        }

        for (int i = size / 2; i >= 0; i--) {
            heapify(i);
        }

    }

    /**
     * Генерация кучи.
     * Если элемент меньше чем один из потомков, то меняем значения с наибольшим потомком.
     * Продолжаем проверять правила для измененного потомка
     * @param i
     */
    private void heapify(int i) {
        debug("heapify " + i + " = " + heap[i]);
        debug(print());
        int left;
        int right;
        int largest;
        while (heap[i] != null) {
            left = i * 2 + 1;
            right = i * 2 + 2;
            largest = i;
            if (left < heap.length && bigger(largest, left)) {
                largest = left;
            }
            if (right < heap.length && bigger(largest, right)) {
                largest = right;
            }
            if (largest == i) {
                break;
            }
            debug("Swap " + i + " <> " + largest);
            V tmp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = tmp;
            i = largest;
            debug(print());
        }
    }

    private void debug(String str) {
        if (isDbg) {
            System.out.println(str);
        }
    }

    private boolean bigger(int a, int b) {
        return heap[b] != null && heap[a].compareTo(heap[b]) < 0;
    }

    /**
     * Сравниваем текущий узел с родителем.
     * Если текущий больше родителя, то обменяться и повторить проверку для родителя
     * @param cur
     */
    private void checkRules(int cur) {
        V curVal = heap[cur];
        int par = getParent(cur);
        while (cur > 0 && heap[par].compareTo(curVal) < 0) {
            heap[cur] = heap[par];
            cur = par;
            par = getParent(cur);
        }
        heap[cur] = curVal;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }


    /**
     * Возвращаем значение из корня.
     * При удалении на место корня устанавливаем последний элемент в куче и запускаем генерацию кучи
     * @return
     */
    @Override
    public V remove() {
        if (size == 0) {
            return null;
        }
        V res = heap[0];
        heap[0] = heap[--size];
        heap[size] = null;
        heapify(0);
        return res;
    }


    @Override
    public int size() {
        return size;
    }

    public String print() {
        VirtualNode<V> root = new VirtualNode<>(heap, 0);
        TreePrinter<VirtualNode<V>> printer = new TreePrinter<>(VirtualNode::getLabel, VirtualNode::getLeft, VirtualNode::getRight);
        printer.setSquareBranches(true);
        printer.setHspace(1);
        printer.setTspace(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        printer.setPrintStream(new PrintStream(byteArrayOutputStream));
        printer.printTree(root);
        return byteArrayOutputStream.toString();

    }

    @Override
    public String toString() {
        return "MyBinaryHeap{size=" + size + '\n' + print() + "\n}";
    }

    static class VirtualNode<V> {
        private int index;
        private V heap[];

        public VirtualNode(V heap[], int index) {
            this.index = index;
            this.heap = heap;
        }

        String getLabel() {
            return String.format("[%d]%s", index, heap[index]);
        }

        VirtualNode<V> getLeft() {
            int left = 2 * index + 1;
            if (check(left)) {
                return null;
            }
            return new VirtualNode<>(heap, left);
        }

        VirtualNode<V> getRight() {
            int right = 2 * index + 2;
            if (check(right)) {
                return null;
            }
            return new VirtualNode<>(heap, right);
        }

        boolean check(int index) {
            return index < 0 || index >= heap.length || heap[index] == null;
        }
    }
}
