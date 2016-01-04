package DataStructureStrArrayDeque;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue_design {
    /*
     * Implement the following operations of a stack using queues.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    empty() -- Return whether the stack is empty.
    Notes:
    You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
    Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
    You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

     */
    // Algorithm: use two stacks, one main stack to keep the offer() data,
    //   the other to assist poll(). optimize by using a flag to switch the main queue

    Queue<Integer> q1;
    Queue<Integer> q2;
    boolean on1;

    public ImplementStackUsingQueue_design() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        on1 = true;
    }

    // Push element x onto stack.
    public void push(int x) {
        if (on1)
            q1.offer(x);
        else {
            q2.offer(x);
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (on1) {  // shift all to queue2 except the last one
            on1 = false;

            if (q1.size() == 0) return;

            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }
            q1.poll();      // throw away the last one

        } else {
            on1 = true;

            if (q2.size() == 0) return;

            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }
            q2.poll();      // throw away the last one            
        }

    }

    // Get the top element.
    public int top() {
        if (on1) {  // shift all to queue2 except the last one
            on1 = false;
            int tmp;
            if (q1.size() == 0) return -1;

            while (q1.size() > 1) {
                q2.offer(q1.poll());
            }
            tmp = q1.poll();
            q2.offer(tmp);

            return tmp;

        } else {
            on1 = true;
            int tmp;
            if (q2.size() == 0) return -1;

            while (q2.size() > 1) {
                q1.offer(q2.poll());
            }
            tmp = q2.poll();
            q1.offer(tmp);
            return tmp;
        }
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return (q1.isEmpty() && q2.isEmpty());
    }

    public static void main(String[] args) {
        ImplementStackUsingQueue_design outer = new ImplementStackUsingQueue_design();
        outer.push(1);
        System.out.println(outer.top());
        System.out.println(outer.empty());
    }
}
