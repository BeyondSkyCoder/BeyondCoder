package leetcode;

/**
 * Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time and O(1) space?
 */

/* Algorithm: reverse the second half, then compare with first half
                       l1 -> l2 -> l3 -> l4 -> l5 -> null
                 dummy             sp          fp
                       l1 -> l2 -> l3 -> l4 -> l5 -> l6 -> null
                                         sp                fp
 */

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        ListNode slow=head, fast = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev, cur;
        // slow is at half, reverse the second half
        prev = slow;
        cur = slow.next;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        // compare with first half
        ListNode l2 = prev, l1 = dummy.next;
        while (l1 != slow) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
