package max.learning.algorithms;


/**
 * <a href="https://leetcode.com/problems/middle-of-the-linked-list/"/>
 *
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 * n1 -> n2 -> n3 -> n4
 */
class MiddleNode {

    public ListNode middleNode(ListNode head) {
        int size = 1;
        ListNode cur = head;
        while (head.next != null) {
            size++;
            head = head.next;
        }
        int i = 0;
        while (i < size / 2) {
            cur = cur.next;
            i++;
        }
        return cur;
    }

    public ListNode middleNodeTwoCursorsSolution(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        if (slow.next == null) return slow;
        if (fast.next.next == null) return fast.next;

        while (fast != null && (fast = fast.next) != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

