package leetcode;

public class RemoveDuplicatesFromSortedList {

	/* 
	 Given a sorted linked list, delete all duplicates such that each element appear only once.

	 For example,
	 Given 1->1->2, return 1->2.
	 Given 1->1->2->3->3, return 1->2->3. 
	*/

    public ListNode deleteDuplicatesI(ListNode head) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (head == null)
            return null;
            
        ListNode prev = head;
        ListNode curr = head.next;
        
        while (curr != null) {
            if (curr.val == prev.val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        
        
        return head;
    }
	
	/*
	 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

	 For example,
	 Given 1->2->3->3->4->4->5, return 1->2->5.
	 Given 1->1->1->2->3, return 2->3. 
			 */
    public ListNode deleteDuplicatesII(ListNode head) {

        if (head == null) {
            return null;
        }
    
        ListNode x = new ListNode(0);
        x.next = head;
        boolean dup;
        
        ListNode cur = head, prev = x;
        
        while (cur != null) {
            dup = false;
            int v = cur.val;
            while (cur.next != null && cur.next.val == v) {
                cur = cur.next;
                dup = true;
            }
            
            if (dup) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            
            cur = cur.next;
        }
        
        return x.next;
    }

    public int removeDuplicates(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int insert_pos = 0;
        int i = 1;
        int val;
        
        if (A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return 1;
        }
        
        val = A[insert_pos];
        insert_pos++;
            
        while(i < A.length) {
            if (A[i] != val) {
                A[insert_pos++] = A[i];
                val = A[i];
            }
            i++;
        }
        
        return insert_pos;
    }
    
    // expert answer
    public int removeDuplicates2(int[] A) {
        if (A.length == 0) return 0;
        int i = 0, j = 1;
        for(; j < A.length; j++){
            if (A[j] != A[j - 1]) {
                A[++i] = A[j];
            }
        }
       return ++i;
    }
    
    /*
     *  Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3]. 
     */
    @SuppressWarnings("PointlessBooleanExpression")
    public int removeDuplicatesAtmostTwice(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int insert_pos = 0;
        int i;
        int len = A.length;
        boolean duplicate_once = false;
        
        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return 1;
        }
        
        int val = A[insert_pos];
        insert_pos++;
        
        i = 1;
        while (i < len) {
            if (A[i] != val) {
                A[insert_pos++] = A[i];
                val = A[i];
                duplicate_once = false;
            } else {
                if (!duplicate_once) {
                    duplicate_once = true;
                    A[insert_pos++] = A[i];
                }
            }
            
            i++;
        }
          
        return insert_pos;
    }
    
    // expert
    public int removeDuplicatesAtmosttwice2(int[] A) {
        if (A.length == 0) return 0;
        int i = 0;
        int j = 1;
        boolean seen = false;
        for(; j < A.length; j ++){
            if (A[j] != A[j - 1]) {
                A[++i] = A[j];
                seen = false;
            }
            else if(!seen){
                A[++i] = A[j];
                seen = true;
            }
        }
       return ++i;
    }
    
}
