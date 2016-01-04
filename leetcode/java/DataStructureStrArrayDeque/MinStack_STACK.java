package DataStructureStrArrayDeque;

import java.util.Stack;

public class MinStack_STACK {
    /*
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
     */

    // Algorithm: two stacks, main stack to keep all data and the minStack only keeps new minumum
    //      when pop from main stack, pop minStack if the number matches
    Stack<Integer> st;
    Stack<Integer> minStack;

    MinStack_STACK() {
        st = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {

        st.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;

        // must use equals !!
        if (st.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        st.pop();
    }

    public int top() {
        if (st.isEmpty()) {
            return -1;
        }
        return st.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack_STACK outer = new MinStack_STACK();
        outer.push(2);
        outer.push(0);
        outer.push(3);
        outer.push(0);
        outer.getMin();
        outer.pop();
        outer.getMin();
        outer.pop();
        outer.getMin();
        outer.pop();
        System.out.println(outer.getMin());
    }
}
