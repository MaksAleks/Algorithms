package max.learning.algorithms.queue;

public interface Queue<T> {

    /**
     * inserts new element into the head of the queue
     * @param t - element to insert
     */
    void put(T t);

    /**
     * extract element from the tail of the queue
     * (element is removed from the queue)
     * @return - element
     */
    T pop();
}
