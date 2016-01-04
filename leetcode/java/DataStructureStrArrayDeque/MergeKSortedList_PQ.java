package DataStructureStrArrayDeque;

import DataStructureLinkedList.ListNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedList_PQ {

	/*
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe
	its complexity. 
	*/

    // Algorithm: use PriorityQueue for fast insertion

    // !!! build the special class, which implements Comparator, to be used by PQ sorting.

    public static final Comparator<ListNode> listComparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode a, ListNode b) {
            return (a.val - b.val);
        }
    };

    public ListNode mergeKLists_PQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, listComparator);
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;

        // get all list into the PQ
        for (ListNode l : lists) {
            if (l != null)
                pq.offer(l);
        }

        // loop:
        //      get the top of PQ out, insert its next element.
        //      PQ automatically does the priority insertion with O(log(n))
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();

            if (node.next != null) {
                pq.offer(node.next);
            }

            res.next = node;
            res = res.next;
        }
        return dummy.next;
    }

    // !!! special wrapper class to accommodate array+index
    //     since it implements Comparable sorting inside, PQ can be used directly
    static class ArrayContainer implements Comparable<ArrayContainer> {
        int[] arr;
        int index;
        public ArrayContainer(int[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public int compareTo(ArrayContainer o) {
            if (this.arr[this.index] > o.arr[o.index]) return 1;
            else if (this.arr[this.index] < o.arr[o.index]) return -1;
            else return 0;
        }
    }

    public int[] mergeKArrays_PQ(int[][] arr) {
        PriorityQueue<ArrayContainer> pq = new PriorityQueue<ArrayContainer>();
        int tot = 0;
        for (int i  = 0; i < arr.length; i++) {
            pq.offer(new ArrayContainer(arr[i], 0));
            tot += arr[i].length;
        }
        int m = 0;
        int result[] = new int[tot];

        while (!pq.isEmpty()) {
            ArrayContainer ac = pq.poll();
            result[m++] = ac.arr[ac.index];

            if (ac.index < ac.arr.length - 1) {
                pq.offer(new ArrayContainer(ac.arr, ac.index+1));
            }
        }

        return result;
    }

    public ListNode mergeKListsSlow(ArrayList<ListNode> lists) {

        int k = lists.size();
        if (k == 0) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = lists.get(0);

        for (int i = 1; i < k; i++) {
            merge2list(dummy, lists.get(i));
        }

        return dummy.next;
    }

    public void merge2list(ListNode head, ListNode q) {
        ListNode p = head.next;
        ListNode prev = head;

        if (p == null) {
            head.next = q;
            return;
        }

        // in-place, no copy
        while (p != null && q != null) {

            if (p.val < q.val) {
                if (p.next == null) {
                    p.next = q;
                    return;
                } else {
                    prev = p;
                    p = p.next;
                }
            } else {
                // insert q, keep the q.next for next cycle
                ListNode tmp = q.next;

                prev.next = q;
                q.next = p;
                prev = q;    // always point to p by default

                q = tmp;
            }
        }

        // no need to check case of (q! =  null && p == null) 
        //	since it's covered by the first half of while loop
    }
}
