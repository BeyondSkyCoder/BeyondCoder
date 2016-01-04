package DataStructureLinkedList;

public class RotateK_LL {

	/* 
     * Given a list, rotate the list to the right by k places, where k is non-negative.

        For example:
        Given 1->2->3->4->5->NULL and k = 2,
        return 4->5->1->2->3->NULL.
	 */

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        int len = 0;

        // find total length
        while (fast != null) {
            fast = fast.next;
            len++;
        }

        if (k % len == 0) {
            return head;
        } else {
            k %= len;
        }

        fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // move fast to the end, then slow is the place to rotate
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }

}
