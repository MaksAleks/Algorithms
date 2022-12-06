package max.learning.algorithms;

import max.learning.algorithms.MiddleNode.ListNode;

/**
 * <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/"/>
 *
 * Given the head of a linked list,
 * remove the nth node from the end of the list and return its head.
 */
class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = head;
        ListNode cur = head;
        for (int i = 1; i < n; i++) {
            cur = cur.next;
        }
        if (cur == null) return null; //possible if and only if list.size == 1
        if (cur.next == null) return head.next; //when removed the last element from the end
        cur = cur.next;
        while (cur.next != null) {
            head = head.next;
            cur = cur.next;
        }
        head.next = head.next.next;
        return start;
    }
}