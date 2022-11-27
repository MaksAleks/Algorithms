package max.learning.algorithms.queue.heap;

import java.util.*;
import java.util.stream.Collectors;

public class Heap<T> implements Iterable<T> {


    public static void main(String[] args) {
        List<Integer> ints = new Random().ints(-10, 10)
                .limit(20)
                .boxed()
                .toList();

        Heap<Integer> heap = new Heap<>(Comparator.<Integer>naturalOrder());
        ints.forEach(i -> {
            heap.add(i, true);
            System.out.println(heap);
        });
    }

    private Comparator<T> comp;
    int size = 0;
    private ArrayList<T> list = new ArrayList<>();

    public Heap(Comparator<T> comparator) {
        this.comp = comparator;
    }

    public Heap(Collection<T> collection, Comparator<T> comp) {
        this.comp = comp;
        this.list.addAll(collection);
        this.size = collection.size();
        buildHeap();
        checkProperty();
    }

    public void add(T e, boolean check) {
        add(e);
        if (check) {
            checkProperty();
        }
    }

    private void checkProperty() {
        // for each non leaf element
        boolean violates = true;
        for (int i = 0; i <= (size - 1) / 2; i++) {
            int l = left(i);
            int r = right(i);
            if (l >= size || r >= size) {
                continue;
            }
            violates = violates &&
                    compare(i, l) <= 0 &&
                    compare(i, r) <= 0;
            if (!violates) {
                System.out.println(this);
                String s = String.format("\ti = get(%d) = %s\n\tl = get(%d) = %s\n\tr = get(%d) = %s",
                        i, get(i),
                        l, get(l),
                        r, get(r)
                );
                throw new IllegalStateException("Heap property violated: \n" + s);
            }
        }
    }

    public void add(T e) {
        list.add(e);
        int cur = size++;
        int pi = parent(cur);
        while (pi != -1) {
            if (compare(pi, cur) > 0) {
                swap(pi, cur);
                cur = pi;
                pi = parent(cur);
            } else {
                return;
            }
        }
    }

    @Override
    public String toString() {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    }

    void buildHeap() {
        if (size == 0) {
            return;
        }

        // Leaves of the heap are:
        // (size - 1)/2 + 1, (size - 1)/2 + 2, ..., size - 1
        // Leaves can be considered as trivial heaps with one - root - element.

        // For each non-leaf element: i = (size-1)/2 downTo 0
        // we can apply heapify(i) procedure.

        // After each invocation of heapify(i) for non-leaf element
        // the heap property (max or min depending on comparator) will be respected.

        for (int i = (size - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    void heapify(int i) {
        int l, r, ext;
        boolean violates = true;
        while (violates) {
            l = left(i);
            r = right(i);
            if (l < size && compare(l, i) < 0) {
                ext = l;
            } else {
                ext = i;
            }
            if (r < size && compare(r, ext) < 0) {
                ext = r;
            }
            violates = ext != i;
            if (violates) {
                swap(ext, i);
                i = ext;
            }
        }
    }

    void swap(int i, int j) {
        T tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }

    private int compare(int i, int j) {
        return comp.compare(get(i), get(j));
    }

    private void set(int i, T t) {
        list.set(i, t);
    }

    T get(int i) {
        return list.get(i);
    }

    private int left(int i) {
        return (i << 1) + 1;
    }

    private int right(int i) {
        return (i + 1) << 1;
    }

    private int parent(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) >> 1;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
