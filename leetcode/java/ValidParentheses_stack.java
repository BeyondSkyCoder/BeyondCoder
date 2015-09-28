package leetcode;

import java.util.Stack;

public class ValidParentheses_stack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp = "()";
		System.out.println("match ? " + isValid(tmp));

	}
	
	/*
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
	 * determine if the input string is valid.
		The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

	 */
    public static boolean isValid(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Stack<Character> ss = new Stack<>();
        for (int i = 0; i < s.length() ; i++) {
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
}
