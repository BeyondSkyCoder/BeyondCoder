package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * I. Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 *
 *
 * II. The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 *
 *  " 3+2*2 " = 7
    " 3/2 " = 1
    " 3+5 / 2 " = 5
 */

/* I. Algorithm: convert to RPN, then calculate
 * II, no bracket, not need to convert it to RPN, in-place evaluation
 */

public class BasicCalculator_I_II {
    public int calculateI(String s) {
        String[] infix = calculatorToStrArray(s);
        String[] rpn = calculatorToRPN(infix);
        return calculateEvalRPN(rpn);
    }

    // convert an expression string to a string array (infix notation)
    private String[] calculatorToStrArray(String s) {
        Deque<String> q = new LinkedList<>();
        int n = s.length(), pos = 0;

        while (pos < n) {
            char c = s.charAt(pos);
            if (Character.isDigit(c)) {         // numbers
                int val = Character.getNumericValue(c);
                while (pos < n - 1 && Character.isDigit(s.charAt(pos + 1))) {
                    val = val * 10 + Character.getNumericValue(s.charAt(++pos));
                }
                q.add(Integer.toString(val));
            } else if (OPERATORS.containsKey(Character.toString(c))) {  // operators
                q.add(Character.toString(c));
            } else if (c == '(' || c == ')') {  // parentheses
                q.add(Character.toString(c));
            }
            pos++;
        }
        // dequeue to a string array
        String[] tokens = new String[q.size()];
        for (int i = 0; i < tokens.length; i++)
            tokens[i] = q.poll();
        return tokens;
    }

    // convert infix notation to reverse polish notation: Shunting-yard algorithm
    private String[] calculatorToRPN(String[] tokens) {
        Deque<String> q = new LinkedList<>();
        Deque<String> stack = new LinkedList<>();    // store operators

        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];

            if (OPERATORS.containsKey(s)) {
                // pop stack to queue if the operator has same or smaller priority
                while (!stack.isEmpty() && !stack.peek().equals("(")
                        && OPERATORS_PRIORITY.get(s) <= OPERATORS_PRIORITY.get(stack.peek())) {
                    q.add(stack.pop());
                }
                stack.push(s);  // push operator to stack
            } else if (s.equals("(")) {
                stack.push(s);  // push "(" to stack
            } else if (s.equals(")")) {
                // pop all operators between "(" and ")" to queue
                while (!stack.peek().equals("("))
                    q.add(stack.pop());
                stack.pop();    // remove "(" from stack, discard it !
            } else {
                q.add(s);       // add number to queue
            }
        }

        // no more token, pop rest of operators in stack to queue
        while (!stack.isEmpty())
            q.add(stack.pop());
        // dequeue to a string array
        String[] rpn = new String[q.size()];
        for (int i = 0; i < rpn.length; i++)
            rpn[i] = q.poll();
        return rpn;
    }

    // evaluate reverse polish notation expression
    private int calculateEvalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();

        for (String s : tokens) {
            if (OPERATORS.containsKey(s)) {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(OPERATORS.get(s).eval(x, y));    // eval runs the anonymous function
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    interface Operator {
        int eval(int x, int y);
    }

    private static final Map<String, Operator> OPERATORS;
    private static final Map<String, Integer> OPERATORS_PRIORITY;

    static {
        OPERATORS = new HashMap<>();
        OPERATORS.put("+", (x, y) -> x + y);
        OPERATORS.put("-", (x, y) -> x - y);
        OPERATORS.put("*", (x, y) -> x * y);
        OPERATORS.put("/", (x, y) -> x / y);
    }

    static {
        OPERATORS_PRIORITY = new HashMap<>();
        OPERATORS_PRIORITY.put("+", 1);
        OPERATORS_PRIORITY.put("-", 1);
        OPERATORS_PRIORITY.put("*", 2);
        OPERATORS_PRIORITY.put("/", 2);
    }


    public int calculateII(String s) {
        String[] infix = calculatorToStrArray(s);
        Deque<String> sq = new LinkedList<>();

        // scan one, process * and / right away
        for (int i = 0; i < infix.length; i++) {
            String token = infix[i];
            if (OPERATORS.containsKey(token)) {
                if (token.equals("*") || token.equals("/")) {
                    int a = Integer.valueOf(sq.pop());
                    int b = Integer.valueOf(infix[i+1]);
                    int c = OPERATORS.get(token).eval(a, b);
                    sq.push(String.valueOf(c));
                    i++;
                } else {    // push in + and -
                    sq.push(token);
                }
            } else {
                sq.push(token);
            }
        }

        // scan two, process + and -. pull from the end of stack
        while (sq.size() > 1) {
            int a = Integer.valueOf(sq.removeLast());
            String op = sq.removeLast();
            int b = Integer.valueOf(sq.removeLast());
            int c = OPERATORS.get(op).eval(a, b);
            // System.out.println("final scan " + a + " " + b + " " + c);
            sq.addLast(String.valueOf(c));
        }

        return Integer.valueOf(sq.pop());
    }

    public static void main(String [] arg) {
        BasicCalculator_I_II outer = new BasicCalculator_I_II();
        String input = "0-12345";
        String input2 = "1-1+1";
        System.out.println(outer.calculateII(input2));
    }
}

