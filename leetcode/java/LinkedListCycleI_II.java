package leetcode;


/*
 * Three pointers: Time ~ O(N), Space ~ O(1) 
用上述快慢指针的方法。slow 和 fast 同时出发，fast 比 slow 快一倍；当 slow 和 fast 相遇时，启动另一个 slow2 以与 slow 相同的速度从头出发，当 slow2 与 slow 相遇，则是环口。
证明：设
x：出发点到环口的距离
a：环口到 slow 和 fast 相遇点的距离
r：环长
当 slow 和 fast 相遇时， slow 走了 x + a，fast 走了 2x + 2a，也就是说在环内 slow 走了 a，fast 走了 x + 2a，可知
a mod r = (x + 2a) mod r
(x + a) mod r = 0
x = kr - a
上式表明当 slow 从 a 出发，此时 slow2 从头出发，当 slow2 走完 x 到达环口时，slow 也真好到达环口，即它们在环口相遇。
 */
public class LinkedListCycleI_II {
	
    // shortest !!!
    public boolean detectCycleI(ListNode head) {
       	ListNode slow = head;
    	ListNode fast = head;
    	
    	while ( fast != null && fast.next != null) {
   			slow = slow.next;
			fast = fast.next.next;
    		if (fast == slow) return true;
    	}
    	return false;
    }
    
    // new one: very SHORT and CORRECT !
    public ListNode detectCycleII(ListNode head) {
       	ListNode slow = head;
    	ListNode fast = head;
    	
    	while ( fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
    		if (fast == slow) {	
    			// find cycle, start head now to chase slow
    			while (head != slow) {
    				head = head.next;
    				slow = slow.next;
    			}
    			return slow;
    		}
    	}  	
    	return null;
    }
}


