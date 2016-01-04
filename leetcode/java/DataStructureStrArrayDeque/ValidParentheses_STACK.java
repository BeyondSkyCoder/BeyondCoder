package DataStructureStrArrayDeque;

import java.util.Stack;

public class ValidParentheses_STACK {

	/*
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
	 * determine if the input string is valid.
	 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
	 * but "(]" and "([)]" are not.
	 */

    // Algorithm: one scan with stack

    public static boolean isValid(String s) {
        Stack<Character> ss = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch.equals('(') || ch.equals('[') || ch.equals('{')) {
                ss.push(ch);
            } else {
                if (ss.isEmpty()) {
                    return false;
                }
                Character expected = ss.pop();
                if (ch.equals(')') && expected.equals('(') ||
                        ch.equals(']') && expected.equals('[') ||
                        ch.equals('}') && expected.equals('{')) {
                } else {
                    return false;
                }
            }
        }

        return ss.isEmpty();
    }

    public static void main(String[] args) {
        String tmp = "()";
        System.out.println("match ? " + isValid(tmp));

    }
}
