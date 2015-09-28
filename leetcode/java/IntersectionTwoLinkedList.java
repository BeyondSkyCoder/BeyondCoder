package leetcode;

/*
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

 * Solution: find length and walk the longer one first offset steps, 
 * 			then move in cadence to check same
 */
public class IntersectionTwoLinkedList {
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int alen = 0, blen = 0, offset;
        ListNode a = headA, b = headB;
        
        while (a != null) {
            a = a.next;
            alen++;
        }
        while (b != null) {
            b = b.next;
            blen++;
        }
        if (alen == 0 || blen == 0) return null;
        
        if (alen > blen) {
            offset = alen - blen;
            a = headA;
            b = headB;
        } else {
            offset = blen - alen;
            // change pointer to make a longer
            a = headB;
            b = headA;
        }
        
        // run a first for offset steps
        while (offset-- > 0) a = a.next;
        
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        
        return a;
    }
}
