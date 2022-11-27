package max.learning.algorithms.queue;

import java.util.Comparator;

public abstract class PriorityQueue<T> implements Queue<T> {

    private Comparator<T> comparator;

    public PriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * insert the element to the queue to the place according to the
     * element's priority, which is defined by the comparator
     * @param t - element to insert
     */
    @Override
    public abstract void put(T t);

    /**
     * extracts element an element with the highest priority
     * @return - element of type T
     */
    @Override
    public abstract T pop();
}
