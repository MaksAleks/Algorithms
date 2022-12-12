package max.learning.algorithms.tree;

import java.util.function.BiConsumer;

public interface Map<K, V> {

    void insert(K k, V v);

    V remove(K k);

    V search(K k);

    void inorderWalk(Walker<K, V> consumer);


    interface Walker<P, Q> {

        void visit(int height, P key, Q value);
    }
}
