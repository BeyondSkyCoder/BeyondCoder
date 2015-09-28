package leetcode;

public class ReverseLinkedListI_II {
	/*
	 Reverse a linked list from position m to n. Do it in-place and in one-pass.

	 For example:
	 Given 1->2->3->4->5->NULL, m = 2 and n = 4,

	 return 1->4->3->2->5->NULL.

	 Note:
	 Given m, n satisfy the following condition:
	 1 ≤ m ≤ n ≤ length of list. 
	*/
	
   public ListNode reverseBetween(ListNode head, int m, int n) {
       // IMPORTANT: Please reset any member data you declared, as
       // the same Solution instance will be reused for each test case.
       ListNode dummy = new ListNode(0);
       dummy.next = head;

       ListNode prev = head, cur = head.next, tmp;
       ListNode orig_start = dummy;
       int count = 1;
   
       while (true) {
           if (count >= m && count < n) {	// reverse pointer, keep prev, cur going
               tmp = cur.next;
               cur.next = prev;
               prev = cur;
               cur = tmp;
           } else if (count < m) {	// keep prev moving
               orig_start = prev;
               prev = cur;
               cur = cur.next;
           } else if (count == n) {	// relink n at m
               // relink
               orig_start.next.next = cur;
               orig_start.next = prev;
               break;
           }
           count++;
       }
       
       return dummy.next;
   }
   
    /*
     * Reverse a singly linked list.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev, cur, next;
        prev = dummy;
        cur = head;
        while (cur.next != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur; cur = next;
        }
        
        // handle the tail and make it new header
        cur.next = prev;
        dummy.next.next = null;
        dummy.next = cur;
        return dummy.next;
    }
}
