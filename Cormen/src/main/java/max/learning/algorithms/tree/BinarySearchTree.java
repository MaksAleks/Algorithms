package max.learning.algorithms.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearchTree<K, V> extends AbstractTree<K, V> {

    public static void main(String[] args) {

        var tree = new BinarySearchTree<Integer, Integer>(Comparator.naturalOrder());

        final List<Integer> ints = List.of(69, 36, -11, -12, 1, -66, 65, -3);
        ints.forEach(i -> {
                    System.out.println("insert: " + i);
                    tree.insert(i, i);
                });

        var list = new ArrayList<>();
        try {
            tree.inorderWalk((h, k, v) -> list.add(k));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        final String treeStr = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(treeStr);

        ints.forEach(i -> {
            System.out.printf("search(%d) = %d\n", i, tree.search(i));
        });
        System.out.println("search(1123) = " + tree.search(1123));

        System.out.println("tree.min() = " + tree.min());
        System.out.println("tree.max() = " + tree.max());
    }


    public BinarySearchTree(Comparator<K> comparator) {
        this.comp = comparator;
    }


    @Override
    public void insert(K k, V v) {
        insert(root, k, v);
    }

    protected Node<K, V> insert(Node<K, V> cur, K k, V v) {
        if (root == null) {
            root = new AbstractTree.Node<>(k, v, null);
            return root;
        }

        AbstractTree.Node<K, V> parent = null;
        while (cur != null) {
            parent = cur;
            if (comp.compare(k, cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }

        }
        cur = parent;

        if (comp.compare(k, cur.key) < 0) {
            cur.left = new AbstractTree.Node<>(k, v, cur);
            return cur.left;
        } else {
            cur.right = new AbstractTree.Node<>(k, v, cur);
            return cur.right;
        }
    }

    /**
     * removes element by specified key
     * @param k - key of the element to be removed
     */
    public V remove(K k) {
        AbstractTree.Node<K, V> cur = findSubtree(k); // O(log(n))
        if (cur == null) {
            return null;
        }

        if (cur.left == null) {
            transplant(cur, cur.right);
        } else if (cur.right == null) {
            transplant(cur, cur.left);
        } else {
            // element to be deleted has both children
            AbstractTree.Node<K, V> min = min(cur.right);
            if (min.parent != cur) {
                transplant(min, min.right);
                min.right = cur.right;
                min.right.parent = cur;
            }
            transplant(cur, min);
            min.left = cur.left;
            min.left.parent = min;
        }
        return cur.value;
    }

    /**
     * the procedure replaces one subtree to another
     *
     * @param from - subtree to be replaced
     * @param to - replacing subtree
     */
    private void transplant(AbstractTree.Node<K, V> from, AbstractTree.Node<K, V> to) {
        if (from == root) {
            root = to;
        }
        if (from.parent.left == from) {
            from.parent.left = to;
        } else {
            from.parent.right = to;
        }
        if (to != null) {
            to.parent = from.parent;
        }
    }
}
