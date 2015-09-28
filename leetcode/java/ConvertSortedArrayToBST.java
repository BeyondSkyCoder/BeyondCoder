package leetcode;

public class ConvertSortedArrayToBST {
 
	public ConvertSortedArrayToBST() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {0, 1, 2};
		TreeNode root = sortedArrayToBST(a);
		
		// boolean haspath = hasPathSum(root, 1);
	}
    
	public static TreeNode sortedArrayToBST(int[] num) {
        
        if (num == null || num.length == 0) return null;
        
        return sortedArrayToBSTHelper(num, 0, num.length-1);
    }

    // use BINARY SEARCH algorithm: 
    // start construction from root because array is randomly accessible
    public static TreeNode sortedArrayToBSTHelper(int[] num, int start, int end) {
        if (start > end) return null;
        
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBSTHelper(num, start, mid-1);
        root.right = sortedArrayToBSTHelper(num, mid+1, end);
        return root;        
    }
    

    // use BINARY SEARCH algorithm: 
    //   the trick is that Linkedlist can only be accessed from head, 
    //   start construction leftmost leaf. used CombinedNode to keep both data structures
    static class CombinedNode {
        ListNode l;
        TreeNode t;

        public CombinedNode(ListNode l, TreeNode t) {
            this.l = l;
            this.t = t;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        ListNode tmp = head;
        
        // get total Linklist length first, the use array-alike index to run the binary search construction
        int num = 0;
        while (tmp != null) { tmp = tmp.next; num++; }
        
        return sortedListToBSTHelper(head, 0, num - 1).t;
    }
    
    public static CombinedNode sortedListToBSTHelper(ListNode head, int start, int end) {
        if (start > end) 
        	return new CombinedNode(head, null);
        
        ListNode tmp;
        
        int mid = (start + end ) / 2;
        CombinedNode left = sortedListToBSTHelper(head, start, mid - 1);
        
        // construct one TreeNode
        tmp = left.l;
        TreeNode parent = new TreeNode(tmp.val);
        parent.left = left.t;
        
        // advance single linked list to next node, use tmp for new head
        //   in deep level recursion(just finish the portion of left=sort(), 
        //      the start,mid,end are very close(0, 0, 1 = one element),
        //		so immediate right leaf is built
        tmp = tmp.next;
        CombinedNode right = sortedListToBSTHelper(tmp, mid + 1, end);
        parent.right = right.t;
        
        return new CombinedNode(right.l, parent);
    }
}
