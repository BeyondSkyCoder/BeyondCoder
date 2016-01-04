package AlgoBinarysearchSort;


import DataStructureLinkedList.ListNode;

public class InsertionSortList_SORT {
    /**
     * Sort a linked list using insertion shellSort.
     */
    // Algorithm: shellSort with linked list has challenge as there is no array index
    //      but since list is good for insertion operation, the swap is reasonably simple

    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev, tmp;
        prev = head;
        head = head.next;

        boolean swapped;

        while (head != null) {
            swapped = false;

            ListNode innerprev = dummy;
            ListNode innercur = dummy.next;

            while (innercur != head) {  // inner loop to find one insertion spot, swap then break;
                if (innercur.val > head.val) {
                    tmp = head.next;
                    innerprev.next = head;
                    head.next = innercur;

                    prev.next = tmp;
                    head = tmp;
                    swapped = true;     // special case and head/prev should not move for outer loop
                    break;
                }

                innercur = innercur.next;
                innerprev = innerprev.next;
            }

            if (!swapped) {
                head = head.next;
                prev = prev.next;
            }
        }

        return dummy.next;
    }
}
