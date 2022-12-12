package max.learning.algorithms.tree;

public class RedBlackTree<K, V> extends AbstractTree<K, V> {

    @Override
    public void insert(K k, V v) {

    }

    @Override
    public V remove(K k) {
        return null;
    }

    /**
     * Left rotate around from and from.right edge.
     * Right child is supposed to be not null
     *
     * @param from - rotating around
     */
    private void leftRot(RBNode<K, V> from) {
        var right = from.right;
        if (right != null) {

            from.right = right.left;
            if (right.left != null) {
                right.left.parent = from;
            }

            right.parent = from.parent;
            if (right.parent == RBNode.NILL) {
                root = right;
            } else if (from.parent.left == from) {
                from.parent.left = right;
            } else {
                from.parent.right = right;
            }

            right.left = from;
            from.parent = right;
        }
    }

    private void rightRot(RBNode<K, V> right) {
        var from = right.left;
        if (from != null) {

            right.left = from.right;
            if (from.right != null) {
                from.right.parent = right;
            }

            from.parent = right.parent;
            if (right.parent == RBNode.NILL) {
                root = from;
            } else if (right.parent.left == right) {
                right.parent.left = from;
            } else {
                right.parent.right = from;
            }

            from.right = right;
            right.parent = from;
        }
    }

    static class RBNode<K, V> extends AbstractTree.Node<K, V> {

        static RBNode<?, ?> NILL = new RBNode<>(null, null, Color.BLACK, null);

        Color color;

        public RBNode(K key, V value, Color color, Node<K, V> parent) {
            super(key, value, parent);
            this.color = color;
        }

    }

    enum Color {
        RED, BLACK;
    }
}
