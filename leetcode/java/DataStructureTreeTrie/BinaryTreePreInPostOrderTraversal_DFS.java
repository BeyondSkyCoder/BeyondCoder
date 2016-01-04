package DataStructureTreeTrie;

import java.util.ArrayList;
import java.util.Stack;

/*
   Tree DFS traversal algorithms are simplified version of corresponding Graph algorithm
        They can be implemented by both recursion and without

        PreOrder, InOrder, PostOrder
        all easy with recursion. without recursion, InOrder/PostOrder are complex
 */

public class BinaryTreePreInPostOrderTraversal_DFS {

    public ArrayList<Integer> orderTraversal(TreeNode root) {

        ArrayList<Integer> tmp = new ArrayList<>();
        orderTraversal_helper(root, tmp);
        return tmp;
    }

    // I. DFS with recursion
    public static void orderTraversal_helper(TreeNode root, ArrayList<Integer> results) {
        if (root == null) return;

        // preOrder
        results.add(root.val);
        orderTraversal_helper(root.left, results);
        orderTraversal_helper(root.right, results);

        // inOrder
        orderTraversal_helper(root.left, results);
        results.add(root.val);
        orderTraversal_helper(root.right, results);

        // postOrder
        orderTraversal_helper(root.left, results);
        orderTraversal_helper(root.right, results);
        results.add(root.val);
    }


    // II DFS without recursion, using STACK

    // A. Preorder
    public ArrayList<Integer> preOrderTraversalNonRecursive(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> sk = new Stack<>();
        sk.push(root);

        while (!sk.isEmpty()) {
            TreeNode tmp = sk.pop();

            result.add(tmp.val);

            // !!! push right before left. so left will pop up first for preOrder
            if (tmp.right != null) sk.push(tmp.right);
            if (tmp.left != null) sk.push(tmp.left);
        }

        return result;
    }
    
	/* 
     * B. Inorder Tree Traversal without Recursion, slightly more complicated

	 * Using Stack is the obvious way to traverse tree without recursion. 
	 * Below is an algorithm for traversing binary tree using stack. 
	 * See this for step wise step execution of the algorithm.

        1) Create an empty stack S.
        2) Initialize current node as root
        3) Push the current node to S and set current = current->left until current is NULL
        4) If current is NULL and stack is not empty then
             a) Pop the top item from stack.
             b) Print the popped item, set current = current->right
             c) Go to step 3.
        5) If current is NULL and stack is empty then we are done.
        Let us consider the below tree for example
                    1
                  /   \
                2      3
              /  \
            4     5

        Step 1 Creates an empty stack: S = NULL

        Step 2 sets current as address of root: current -> 1

        Step 3 Pushes the current node and set current = current->left until current is NULL
             current -> 1
             push 1: Stack S -> 1
             current -> 2
             push 2: Stack S -> 2, 1
             current -> 4
             push 4: Stack S -> 4, 2, 1
             current = NULL

        Step 4 pops from S
             a) Pop 4: Stack S -> 2, 1
             b) print "4"
             c) current = NULL // (right of 4) and go to step 3
        Since current is NULL step 3 doesn't do anything.

        Step 4 pops again.
             a) Pop 2: Stack S -> 1
             b) print "2"
             c) current -> 5 // right of 2 and go to step 3

        Step 3 pushes 5 to stack and makes current NULL
             Stack S -> 5, 1
             current = NULL

        Step 4 pops from S
             a) Pop 5: Stack S -> 1
             b) print "5"
             c) current = NULL //right of 5 and go to step 3
        Since current is NULL step 3 doesn't do anything

        Step 4 pops again.
             a) Pop 1: Stack S -> NULL
             b) print "1"
             c) current -> 3 // right of 5

        Step 3 pushes 3 to stack and makes current NULL
             Stack S -> 3
             current = NULL

        Step 4 pops from S
             a) Pop 3: Stack S -> NULL
             b) print "3"
             c) current = NULL // right of 3

        Traversal is done now as stack S is empty and current is NULL.
	*/

    public ArrayList<Integer> inOrderTraversalNonRecursive(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<TreeNode> sk = new Stack<TreeNode>();

        // A. as long as there is left, continue (push itself, then move to left)
        TreeNode tmp = root;
        while (tmp != null) {
            sk.push(tmp);
            tmp = tmp.left;
        }

        // B. when pop happens, it means left side is done, always move to right
        //    for the new popped up node, if its right is not null, like above, push
        //    leftwing to the end before proceed next in stack
        while (!sk.isEmpty()) {
            TreeNode nextNode = sk.pop();
            result.add(nextNode.val);

            tmp = nextNode.right;
            while (tmp != null) {
                sk.push(tmp);
                tmp = tmp.left;
            }
        }

        return result;
    }

    // C. postOrder with NON-recursive and NO FLAG; more complex than preOrder.

    public ArrayList<Integer> postOrderTraversalNonRecursion1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;

        Stack<TreeNode> sk = new Stack<TreeNode>();
        TreeNode prev = new TreeNode(0);

        // 		the first trick is NOT pop root(keep it pushed in) before traverse and print
        //      the next trick is find termination condition and when pop.
        //          leaf node is easy, pop immediately. then when to pop intermediate ones ? 
        //			the answer is to remember previous poped one. If that is one of child, 
        //			  pop current intermediate one.

        sk.push(root);
        while (sk.size() > 0) {
            TreeNode tmp = sk.peek();

            if (tmp.left == null && tmp.right == null ||
                    tmp.right == prev || tmp.left == prev) {
                sk.pop();
                result.add(tmp.val);
                prev = tmp;
            } else {
                if (tmp.right != null) sk.push(tmp.right);
                if (tmp.left != null) sk.push(tmp.left);
            }
        }

        return result;
    }
}
