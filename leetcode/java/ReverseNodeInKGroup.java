package leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5
 */

// Algorithm: like reverse-linked-list-II (in-place)


public class ReverseNodeInKGroup {
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
                prev = reverseLinkList(prev, next);
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

    do in-place is complex, seperate function for reverse
    */
    public ListNode reverseLinkList(ListNode pre, ListNode next) {
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
