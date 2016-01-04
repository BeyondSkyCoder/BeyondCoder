package AlgoTwoPointers;

import DataStructureLinkedList.ListNode;

public class RemoveDuplicatesFromSortedLinkedList_2P {

	/* 
     Given a sorted linked list, delete all duplicates such that each element appear only once.

	 For example,
	 Given 1->1->2, return 1->2.
	 Given 1->1->2->3->3, return 1->2->3. 
	*/
    // Algorithm: basic, move prev and cur pointer

    public ListNode deleteDuplicatesI(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (curr.val == prev.val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }


        return head;
    }

    /*
     Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

     For example,
     Given 1->2->3->3->4->4->5, return 1->2->5.
     Given 1->1->1->2->3, return 2->3.
    */

    public ListNode deleteDuplicatesII(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode x = new ListNode(0);
        x.next = head;
        boolean dup;

        ListNode cur = head, prev = x;

        while (cur != null) {
            dup = false;
            int v = cur.val;
            while (cur.next != null && cur.next.val == v) {
                cur = cur.next;
                dup = true;
            }

            if (dup) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }

            cur = cur.next;
        }

        return x.next;
    }
}
