package leetcode;

import java.util.Stack;

/*
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
 */

public class MinStack {
    Stack<Integer> st;
    Stack<Integer> minStack;
    
    MinStack() {
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
        if ( st.peek().equals( minStack.peek() ) ) {
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
}

class MinStack_lessefficient {
    Stack<Integer> st;
    int minval;
    
    MinStack_lessefficient() {
        st = new Stack<>();
        minval = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        st.push(x);
        if (x < minval) {
            minval = x;
        }
    }

    public void pop() {
        if (st.isEmpty())
            return;
            
        int x = st.pop();
        
        // update minval
        if (x == minval) {
            minval = Integer.MAX_VALUE;
            Stack<Integer> newstack = new Stack<>();
            int y;
            while (!st.isEmpty()) {
                y = st.pop();
                System.out.println("pop out y " + y);
                if (y <= minval) {
                    minval = y;
                }
                newstack.push(y);
            }
            // recover
            while (!newstack.isEmpty()) {
                st.push(newstack.pop());
            }
        }
    }

    public int top() {
        if (st.isEmpty()) {
            return -1;
        }
        return st.peek();
    }

    public int getMin() {
        return minval;
    }
    
    public static void main(String[] args) {
    	MinStack outer = new MinStack();
    	outer.push(2);outer.push(0);outer.push(3);outer.push(0);
    	outer.getMin();
    	outer.pop();
    	outer.getMin();
    	outer.pop();
    	outer.getMin();
    	outer.pop();
    	System.out.println(outer.getMin());
    }
}
