package max.learning.algorithms.tree;

import java.util.Comparator;

public abstract class AbstractTree<K, V> implements Map<K, V> {

    protected Node<K, V> root;

    protected Comparator<K> comp;

    public V min() {
        return min(root).value;
    }

    protected AbstractTree.Node<K, V> min(AbstractTree.Node<K, V> cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public V max() {
        return max(root).value;
    }

    protected AbstractTree.Node<K, V> max(AbstractTree.Node<K, V> cur) {
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    public V search(K key) {
        return findSubtree(key).value;
    }


    protected AbstractTree.Node<K, V> findSubtree(K key) {
        AbstractTree.Node<K, V> cur = root;
        while (cur != null && comp.compare(key, cur.key) != 0) {
            if (comp.compare(key, cur.key) < 0) {
                cur = cur.left;
            } else if (comp.compare(key, cur.key) > 0) {
                cur = cur.right;
            }
        }
        return cur;
    }


    @Override
    public void inorderWalk(Walker<K, V> visitor) {
        inorderWalk(root, visitor);
    }

    public void preorderWalk(Walker<K, V> visitor) {
        preorderWalk(0, root, visitor);
    }

    protected void preorderWalk(int level, Node<K,V> cur, Walker<K, V> visitor) {
        if (root == null) {
            return;
        }
        if (cur != null) {
            visitor.visit(level, cur.key, cur.value);
            level++;
            preorderWalk(level, cur.left, visitor);
            preorderWalk(level, cur.right, visitor);
        }
    }

    protected void inorderWalk(Node<K, V> cur, Walker<K, V> visitor) {

        Node<K, V> from = cur.parent;

        int level = 0;
        while (true) {
            if (from == cur.parent) {
                from = cur;
                if (cur.left != null) {
                    cur = cur.left;
                    level++;
                } else if (cur.right != null) {
                    visitor.visit(level, cur.key, cur.value);
                    cur = cur.right;
                    level++;
                } else {
                    visitor.visit(level, cur.key, cur.value);
                    if (cur == root) {
                        return;
                    }
                    cur = cur.parent;
                    level--;
                }
            } else if (from == cur.left) {
                visitor.visit(level, cur.key, cur.value);
                from = cur;
                if (cur.right != null) {
                    cur = cur.right;
                    level++;
                } else {
                    if (cur == root) {
                        return;
                    }
                    cur = cur.parent;
                    level--;
                }
            } else if (from == cur.right) {
                if (cur == root) {
                    return;
                }
                from = cur;
                cur = cur.parent;
                level--;
            }
        }
    }

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return String.format("{ value: %s, parent: %s, left: %s, right: %s }",
                    value,
                    parent == null ? null : parent.value,
                    left == null ? null : left.value,
                    right == null ? null : right.value
            );
        }
    }
}
