package max.learning.algorithms.tree;

import java.util.Comparator;
import java.util.List;

/**
 *
 * AVL tree is height-balanced BST:
 *  for each node the difference in heights of its subtrees can't exceed 1: |hL - hR| <= 1
 */
public class AVLTree<K, V> extends BinarySearchTree<K,V> {

    public static void main(String[] args) {
        var avl = new AVLTree<Integer, Integer>(Comparator.naturalOrder());
        List.of(20, 15, 5, 40, 50, 18).forEach(i -> {
            avl.insert(i, i);
        });
        avl.preorderWalk((h, k, v) -> {
            String indentation = "\t".repeat(h + 1);
            System.out.printf("%s " + k + "%n", indentation);
        });
    }

    public AVLTree(Comparator<K> comparator) {
        super(comparator);
    }

    @Override
    public void insert(K k, V v) {
        Node<K, V> inserted = insert(root, k, v); // O(log n)
        Node<K, V> imbalanced = imbalancedAncestor(inserted); // O(log n)


    }



    private Node<K, V> imbalancedAncestor(Node<K, V> cur) {
        while (cur.parent != null) {
            cur = cur.parent;
            if (balanceFactor(cur) > 1) {
                return cur;
            }
        }
        return null;
    }

    private int balanceFactor(Node<K, V> node) {
        return Math.abs(leftHeight(node) - rightHeight(node));
    }

    private int leftHeight(Node<K, V> node) {
        final Counter height = new Counter();
        inorderWalk(node.left, (h, k, v) -> {
            if (h > height.counter) {
                height.counter = h;
            }
        });
        return height.counter;
    }

    private int rightHeight(Node<K, V> node) {
        final Counter height = new Counter();
        inorderWalk(node.right, (h, k, v) -> {
            if (h > height.counter) {
                height.counter = h;
            }
        });
        return height.counter;
    }

    private static class Counter {
        int counter;
    }

    @Override
    public V remove(K k) {
        return null;
    }
}
