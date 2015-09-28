package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList_priorityqueue {
	
	/*
	Merge k sorted linked lists and return it as one sorted list. Analyze and describe 
	its complexity. 
	*/
	
	// Algorithm: use PriorityQueue for fast insertion
	
	// build the comparator first !
	public static final Comparator<ListNode> listComparator = new Comparator<ListNode> () {
		@Override
		public int compare(ListNode a, ListNode b) {
			return (a.val - b.val);
		}
	};
	
	public ListNode mergeKListsFastwithPriorityQueue(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;

		PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, listComparator);
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		
		for (ListNode tmp : lists) {
			if (tmp != null)
				pq.offer(tmp);
		}
		
		while (!pq.isEmpty()) {
			ListNode node = pq.poll();
			
			if (node.next !=  null) {
				pq.offer(node.next);	// PQ automatically does the priority insertion with O(log(n))
			}
			
			p.next = node;
			p = p.next;
		}
		return dummy.next;
	}
	
    public ListNode mergeKListsSlow(ArrayList<ListNode> lists) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        
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
                    prev = q;	// always point to p by default
                    
                    q = tmp;
                }
        }
        
        // no need to check case of (q! =  null && p == null) 
        //	since it's covered by the first half of while loop
                    
    }
}
