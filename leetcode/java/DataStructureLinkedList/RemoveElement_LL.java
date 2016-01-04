package DataStructureLinkedList;

public class RemoveElement_LL {
    /*
     * Remove all elements from a linked list of integers that have value val.

        Example
        Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
        Return: 1 --> 2 --> 3 --> 4 --> 5
     */
    // Algorithm:
    //  very basic findAndDelete

    public ListNode scanAndRemoveElements(ListNode head, int target) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == target) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
