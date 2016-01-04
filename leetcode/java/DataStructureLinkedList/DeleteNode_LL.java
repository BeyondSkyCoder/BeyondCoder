package DataStructureLinkedList;

public class DeleteNode_LL {
    /*
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

        Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
        the linked list should become 1 -> 2 -> 4 after calling your function.
     */
    // Algorithm: no prev pointer, the trick to skip next node by copying its value to current node
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }

        ListNode next = node.next;
        ListNode nextnext = next.next;

        node.val = next.val;
        node.next = nextnext;
    }
}
