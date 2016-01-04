package DataStructureStrArrayDeque;

import java.util.Stack;

public class ImplementQueueUsingStack_design {
    /*
     * Implement the following operations of a queue using stacks.

    push(x) -- Push element x to the back of queue.
    pop() -- Removes the element from in front of queue.
    peek() -- Get the front element.
    empty() -- Return whether the queue is empty.
    Notes:
    You must use only standard operations of a stack -- which means
    only push to top, peek/pop from top, size, and is empty operations are valid.
    Depending on your language, stack may not be supported natively.
    You may simulate a stack by using a list or deque (double-ended queue),
    as long as you use only standard operations of a stack.
    You may assume that all operations are valid (for example, no pop or
    peek operations will be called on an empty queue).
     */

    // Algorithm: use two stacks, one main stack to keep the push data, the other to assist pop

    Stack<Integer> s1;
    Stack<Integer> s2;

    ImplementQueueUsingStack_design() {
        super();
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while (s1.size() > 1) {
            s2.push(s1.pop());
        }
        s1.pop();
        while (s2.size() > 0) {
            s1.push(s2.pop());
        }
    }

    // Get the front element.
    public int peek() {
        int tmp = -1;
        if (s1.size() == 0) return tmp;

        while (s1.size() > 1) {
            s2.push(s1.pop());
        }
        tmp = s1.peek();
        while (s2.size() > 0) {
            s1.push(s2.pop());
        }
        return tmp;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty();
    }
}
