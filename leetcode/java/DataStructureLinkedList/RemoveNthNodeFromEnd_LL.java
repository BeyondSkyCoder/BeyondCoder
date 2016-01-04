package DataStructureLinkedList;

public class RemoveNthNodeFromEnd_LL {

	/* 
     * Given a linked list, remove the nth node from the end of list and return its head.
	 * For example,
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * After removing the second node from the end, the linked list becomes 1->2->3->5.

        Note:
        Given n will always be valid.
        Try to do this in one pass.
	 */
    // Algoithm:
    //      two pointers: one Nth apart advanced
    //      when advanced one reaches the end, the behind one is the spot to remove

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // move p1 to the Nth after head;
        ListNode p1 = head;
        for (int i = 0; i < n; i++) {
            if (p1 != null) {
                p1 = p1.next;
            } else {    // input list is shorter than n, nothing to remove, return head
                return head;
            }
        }

        ListNode p2 = dummy;    // cover the case of p1 is already null
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;
        return dummy.next;
    }
}
