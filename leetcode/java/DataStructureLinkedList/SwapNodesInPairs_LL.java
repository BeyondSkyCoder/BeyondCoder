package DataStructureLinkedList;

public class SwapNodesInPairs_LL {

	/* 
     *  Given a linked list, swap every two adjacent nodes and return its head.

        For example,
        Given 1->2->3->4, you should return the list as 2->1->4->3.

        Your algorithm should use only constant space. You may not modify the values in the list,
        only nodes itself can be changed.
	 */

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode curr = dummy;
        while (curr.next != null && curr.next.next != null) {
            // rehook the new head after swap
            curr.next = swapPair(curr.next);
            curr = curr.next.next;    // move forward two steps
        }

        return dummy.next;
    }

    public ListNode swapPair(ListNode leftpair) {
        ListNode rightpair = leftpair.next;
        leftpair.next = rightpair.next;
        rightpair.next = leftpair;

        return rightpair;
    }

}
