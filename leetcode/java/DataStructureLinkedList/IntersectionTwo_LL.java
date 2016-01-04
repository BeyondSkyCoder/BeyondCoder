package DataStructureLinkedList;

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

 * Algorithm:
 *      find length and walk the longer one first for offset steps,
 * 		then move in cadence to check collision
 */
public class IntersectionTwo_LL {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int alen = 0, blen = 0, offset;
        ListNode a = headA, b = headB;

        // 1. find the length
        while (a != null) {
            a = a.next;
            alen++;
        }
        while (b != null) {
            b = b.next;
            blen++;
        }
        if (alen == 0 || blen == 0) {
            return null;
        }

        // 2. ensure a is longer than b
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

        // 3. run longers one for offset steps first
        while (offset-- > 0) a = a.next;

        // 4. run two lists simultaneously to find the intersection
        while (a != b) {
            a = a.next;
            b = b.next;
        }

        return a;
    }
}
