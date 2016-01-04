package DataStructureLinkedList;

public class CycleI_II_LL_2P {
    /*
        Given a linked list, determine if it has a cycle in it.
     */
    // Algorithm: move slow and fast pointers, where fast is twice as much as slow
    // Note this code is optimized

    public boolean detectCycleI(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /*
     *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     *  move slow and fast pointers, where fast is twice as much as slow
     *  when slow and fast meet,
     *      start another slow2 from the head
     *
     *  proof,
     *      x: the distance from head to circle entry
     *      a: circle entry to meet point
     *      r: circle length
     *      when slow and fast meet, slow moves x + a and fast moves 2x + 2a
     *          i.e. within the circle, slow moves a and fast moves x + 2a.
     *          because a mode r == (x + 2a) mod r, we know (x + a) mod r == 0, or x = k * r -a
     *      with above, slow2 finishes x to reach circle entry, slow should reach circle entry as well
     *      their meet point is the circle entry.
     *      DONE.
     */

    // optimized
    public ListNode detectCycleII(ListNode head) {
        ListNode slow = head, slow2 = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                // find cycle, start head now to chase slow
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}


