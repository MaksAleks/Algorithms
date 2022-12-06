package max.learning.algorithms.sort;

public class HeapSort implements Sort {

    public static void main(String[] args) {
        var sort = new HeapSort();
        for (int i = 0; i < 1000; i++) {
            sort.test(i);
        }
        System.out.println("Heap sort works fine");
    }

    private int[] arr;

    private int size; // heap size. Might me <= arr.length

    @Override
    public int[] sort(int[] input) {
        this.arr = input;
        buildMaxHeap();
        while (size > 1) {
            swap(0, size - 1);
            size--;
            maxHeapifyLoop(0);
        }
        return arr;
    }

    /**
     * Build max heap from the array arr
     * using {@link this#maxHeapifyRecursive(int)} or {@link this#maxHeapifyLoop(int)}
     * <p>
     * Semantics:
     * Leafs of the heap have indexes: (size - 1) / 2 + 1
     * Each of them can be considered a trivial max heap of one element
     * We can apply maxHeapify procedures to non leaf elements: i = from (arr.length - 1) / 2 downTo 0
     * <p>
     * After each execution of maxHeapify, next i becomes the root of a max heap
     */
    public void buildMaxHeap() {
        size = arr.length;
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            maxHeapifyRecursive(i);
        }
        checkMaxHeapProperty();
    }

    public void checkMaxHeapProperty() {
        final int p = parent(arr.length - 1);
        for (int i = 0; i <= p; i++) {
            if (parent(left(i)) != i) {
                String error = String.format("Parent(left(%d)) = %d\n", i, parent(left(i)));
                throw new IllegalStateException("max heap property violated: " + error);
            }
            if (parent(right(i)) != i) {
                String error = String.format("Parent(right(%d)) = %d\n", i, parent(right(i)));
                throw new IllegalStateException("max heap property violated: " + error);
            }
        }
        for (int i = 0; i <= p; i++) {
            int l = left(i);
            int r = right(i);
            try {
                if ((l < size && arr[i] < arr[l]) || (r < size && arr[i] < arr[r])) {
                    throw new IllegalStateException("max heap property violated");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                String error = String.format(
                        "\ni = %d\n" +
                        "l = %d\n" +
                        "r = %d\n" +
                        "parent(arr.length - 1) = %d",
                        i, l, r, p
                );
                throw new IllegalStateException("max heap property violated: " + error, ex);
            }
        }
    }

    /**
     * This procedure tries to support
     * the property of max heap: A[i] >= A[Parent(i)]
     * <p>
     * Semantics:
     * if A[i] violates the property:
     * put A[i] down to the rite place
     *
     * @param idx - index of element in arr
     */
    public void maxHeapifyRecursive(int idx) {
        int l = left(idx);
        int r = right(idx);
        int max;
        if (l < size && arr[l] > arr[idx]) {
            max = l;
        } else {
            max = idx;
        }
        if (r < size && arr[r] > arr[max]) {
            max = r;
        }
        if (max == idx) return;
        // swap violating element with max(l, r)
        swap(idx, max);

        // after that the heap property might be violated for max(l, t)
        // and we should ensure the prop for max(l, t) as well
        maxHeapifyRecursive(max);
    }

    public void maxHeapifyLoop(int idx) {
        boolean violates = true;
        int l, r, max;
        while (violates) {
            l = left(idx);
            r = right(idx);
            if (l < size && arr[l] > arr[idx]) {
                max = l;
            } else {
                max = idx;
            }
            if (r < size && arr[r] > arr[max]) {
                max = r;
            }
            violates = max != idx;
            if (violates) {
                swap(max, idx);
                idx = max;
            }
        }
    }

    private void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    int left(int idx) {
        // (idx << 1) must be in braces!
        // + has higher priority than <<
        return (idx << 1) + 1;
    }

    int right(int idx) {
        return (idx + 1) << 1;
    }

    int parent(int idx) {
        return (idx - 1) >> 1;
    }
}
