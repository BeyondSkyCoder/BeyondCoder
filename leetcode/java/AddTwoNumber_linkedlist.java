package leetcode;

public class AddTwoNumber_linkedlist {

	/*
	 * You are given two linked lists representing two non-negative numbers. 
	 * The digits are stored in reverse order and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.

	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ListNode dummy = new ListNode(0);
        ListNode cur = new ListNode(0);
        dummy.next = cur;
        
        int sum = 0;
        int carry = 0;
        
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            } else {
                carry = 0;
            }
        
            ListNode elem = new ListNode(sum);
            cur.next = elem;	// add to the head due to single linked list
            cur = elem;
                
            l1 = l1.next;
            l2 = l2.next;
        }
        
        // add the remaining
        if (l1 == null) {
            while (l2 != null) {
                sum = l2.val + carry;
                if (sum >= 10) {
                    carry = 1;
                    sum %= 10;
                } else {
                    carry = 0;
                }
                
                ListNode elem = new ListNode(sum);
                cur.next = elem;
                cur = elem;
                l2 = l2.next;
            }
        } else {
            while (l1 != null) {
                sum = l1.val + carry;
                if (sum >= 10) {
                    carry = 1;
                    sum %= 10;
                } else {
                    carry = 0;
                }
            
                ListNode elem = new ListNode(sum);
                cur.next = elem;
                cur = elem;
                l1 = l1.next;
            }
        }
        
        if (carry != 0) {
                ListNode elem = new ListNode(carry);
                cur.next = elem;
                cur = elem;
        }
        return dummy.next.next;
    }

}
