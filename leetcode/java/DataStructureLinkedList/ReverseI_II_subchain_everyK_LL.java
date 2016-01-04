package DataStructureLinkedList;

public class ReverseI_II_subchain_everyK_LL {
    /*
	 Reverse a linked list from position m to n. Do it in-place and in one-pass.

	 For example:
	 Given 1->2->3->4->5->NULL, m = 2 and n = 4,

	 return 1->4->3->2->5->NULL.

	 Note:
	 Given m, n satisfy the following condition:
	 1 ≤ m ≤ n ≤ length of list. 
	*/
    // Algorithm: three pointers: prev-cur-tmp, handle before-in-after three segment cases

    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = head, cur = head.next, tmp;
        ListNode orig_start = dummy;
        int count = 1;

        while (true) {
            if (count >= m && count < n) {    // reverse pointer, keep prev, cur going
                tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            } else if (count < m) {    // keep prev moving
                orig_start = prev;
                prev = cur;
                cur = cur.next;
            } else if (count == n) {    // relink n at m
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
    // Algorithm: basic reverse with 3 pointers: prev-cur-next

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev, cur, next;
        prev = dummy;
        cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // handle the tail and make it new header
        dummy.next.next = null;
        return prev;
    }

    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     * Only constant memory is allowed.
     * For example,
     * Given this linked list: 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     */

    // Algorithm: two pointers: cur-next

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 0) return null;
        if (k == 0 || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 0;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            count++;
            ListNode next = cur.next;
            if (count == k) {
                // when accumulate to k, reverse use the pre and next pointer
                prev = reverseSubChain_LL(prev, next);
                count = 0;
            }
            cur = next;
        }
        return dummy.next;
    }

    /*
        Reverse a link list between pre and next exclusively
        an example:
        a linked list:
        0->1->2->3->4->5->6
        |           |
        pre        next
        after call pre = reverse(pre, next)
        0->3->2->1->4->5->6
             |  |
             pre next

        do in-place
    */
    // Algorithm: basic reverse a segment

    public ListNode reverseSubChain_LL(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode cur = last.next;

        while (cur != next) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
}
