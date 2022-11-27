package max.learning.algorithms.queue.heap;

import max.learning.algorithms.queue.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HeapPriorityQueue<T> extends PriorityQueue<T> {

    public static void main(String[] args) {
        var queue = new HeapPriorityQueue<Integer>(Comparator.naturalOrder());
        new Random().ints(-10, 10)
                .limit(20)
                .boxed()
                .forEach(queue::put);

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.pop());
        }
        String q = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(q);
    }

    private final Heap<T> heap;

    public HeapPriorityQueue(Comparator<T> comparator) {
        super(comparator);
        this.heap = new Heap<>(comparator);
    }

    @Override
    public void put(T t) {
        heap.add(t);
    }

    public boolean isEmpty() {
        return heap.size == 0;
    }

    @Override
    public T pop() {
        if (heap.size == 0) {
            return null;
        }
        T t = heap.get(0);
        heap.swap(0, heap.size - 1);
        heap.size--;
        heap.heapify(0);
        return t;
    }
}
