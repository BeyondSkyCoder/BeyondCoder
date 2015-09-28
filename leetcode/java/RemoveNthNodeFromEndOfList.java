package leetcode;

public class RemoveNthNodeFromEndOfList {

	/* 
	 * Given a linked list, remove the nth node from the end of list and return its head.
	 * For example,
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * After removing the second node from the end, the linked list becomes 1->2->3->5.

	Note:
	Given n will always be valid.
	Try to do this in one pass. 
	 */

	public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // move p1 to the Nth after head;
        ListNode p1 = head;
        for (int i = 0; i < n; i++) {
            if (p1 != null) p1 = p1.next;
            else
            	return head;
        }
        
        ListNode p2 = head;
        if (p1 == null) {	// p1 is at the tail, need to move head as Nth before
            dummy.next = p2.next;
            return dummy.next;
        }
        
        // go through till p1 hits the tail
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p2.next = p2.next.next;
        return dummy.next;
    }
    
	// a little trickier, p2 starts from dummy to skip extra steps.
	public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode p1 = head;
        for (int i = 0; i < n; i++) {
            if (p1 != null) p1 = p1.next;
            else
                return head;
        }
        
        ListNode p2 = dummy;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p2.next = p2.next.next;
        return dummy.next;
	}
}
