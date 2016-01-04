package DataStructureLinkedList;

public class CopyListWithRandomPointer_LL {
    /*
     * A linked list is given such that each node contains an additional random pointer which could point to
     * any node in the list or null. Return a deep copy of the list.
     */

    // Algorithm: three pass scans

    /* Definition for singly-linked list with a random pointer */
    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        RandomListNode dummy = new RandomListNode(0);
        RandomListNode tmp, cur;
        dummy.next = head;

        cur = head;

        // I, copy w/o random pointer and insert after existing node
        while (cur != null) {
            tmp = new RandomListNode(cur.label);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // II, copy random pointer
        cur = head;
        while (cur != null) {
            tmp = cur.next;
            if (cur.random != null) {
                tmp.random = cur.random.next;
            }
            cur = tmp.next;
        }

        // III decouple the two linkedlist
        cur = head;
        dummy.next = head.next;
        while (cur != null) {
            tmp = cur.next;
            cur.next = tmp.next;
            if (tmp.next != null)
                tmp.next = tmp.next.next;
            cur = cur.next;
        }

        return dummy.next;
    }
}
