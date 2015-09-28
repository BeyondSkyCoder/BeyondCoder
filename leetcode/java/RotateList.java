package leetcode;

public class RotateList {

	/* 
	 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
	 */
	
    public ListNode rotateRight(ListNode head, int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head;        
        ListNode fast = head;
        int len = 0;

        // find total length
        while (fast !=null) {
            fast = fast.next;
            len++;
        }
        
        if (n % len == 0) {
        	return head;
        } else {
            n %= len;
        }

        fast = head;
        for (int i = 0; i < n; i++) {
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
