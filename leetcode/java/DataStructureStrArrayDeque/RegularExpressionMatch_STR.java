package DataStructureStrArrayDeque;

public class RegularExpressionMatch_STR {

    /*
     * Regular Expression Match -- isMatch(String, Pattern)
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).

     * 	Some examples:
     * 	isMatch("aa","a") → false
     *  isMatch("aa","aa") → true
     * 	isMatch("aaa","aa") → false
     * 	isMatch("aa", "a*") → true
     * 	isMatch("aa", ".*") → true
     * 	isMatch("ab", ".*") → true
     * 	isMatch("aab", "c*a*b") → true
     *
     * Algorithm: [HARD] recursion
     * 	1. compare one character each recursion
     *  2. the difficult is to handle '*'. there are two major cases
     *  	2.1: p's next is not ’*’，which is simple
            2.2: p's next is ’*’，which is complex
     */
    // Bonus problem: Match . and * and +
    public boolean isMatchWithPlus(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }

        if (p.length() == 1) {
            if (s.length() < 1) {
                return false;
            } else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else { // continue comparing next char
                return isMatchWithPlus(s.substring(1), p.substring(1));
            }
        } else if (p.charAt(1) != '*' && p.charAt(1) != '+') {            // case 2: when p.next is not * and +
            if (s.length() < 1) {
                return false;
            } else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else { // continue comparing next char
                return isMatchWithPlus(s.substring(1), p.substring(1));
            }
        } else { // p.charAt(1) == '*' || p.charAt(1) == '+'

            // when the '*' stands for 0 preceding element, comparing with p after this one
            if (p.charAt(1) == '*' && isMatchWithPlus(s, p.substring(2))) {
                return true;
            }

            // when the */+ stands for 1 or more preceding element, try every possible number
            int i = 0;
            while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    // same as isMatch, more compact, a little hard to read
    public boolean isMatchCompact(String s, String p) {
        if (p.length() == 0) {
            return (s.length() == 0);
        }

        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() == 0 || (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        } else {
            int i = -1;
            while (i < s.length() && (i == -1 || ((s.charAt(i) == p.charAt(0)) || p.charAt(0) == '.'))) {
                if (isMatch(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

    // Original problem: Match . and *
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }

        //case 1: when the second char of p is NOT '*', simple case
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            } else if ((p.charAt(0) != s.charAt(0)) && (p.charAt(0) != '.')) {
                return false;
            } else { // continue comparing next char
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        //case 2: when the second char of p is '*', complex case
        else {    // p.charAt(1) == '*'

            // when the '*' stands for 0 preceding element, comparing with p after this one
            if (isMatch(s, p.substring(2))) {
                return true;
            }

            // when the '*' stands for 1 or more preceding element, try every possible number
            int i = 0;
            while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }


    public static void main(String[] args) {
        boolean res;

        RegularExpressionMatch_STR outer = new RegularExpressionMatch_STR();

        System.out.println("match aa, a -> " + outer.isMatch("aa", "a"));
        System.out.println("match aa, aa -> " + outer.isMatch("aa", "aa"));
        System.out.println("match aaa, aa -> " + outer.isMatch("aaa", "aa"));
        System.out.println("match aa, a* -> " + outer.isMatch("aa", "a*"));
        System.out.println("match aa, .* -> " + outer.isMatch("aa", ".*"));
        System.out.println("match ab, .* -> " + outer.isMatch("ab", ".*"));

        // below is true as * means 0 or more. so c can be ignored
        System.out.println("match aab, c*a*b -> " + outer.isMatchCompact("aab", "c*a*b"));
        // but this is false as d can be ignored, but c in front can't be ignored as * can only help one front
        System.out.println("match aab, cd*a*b -> " + outer.isMatchCompact("aab", "cd*a*b"));

        // + can match 1 or more
        System.out.println("match caab, ca+b -> " + outer.isMatchWithPlus("caab", "ca+b"));
        System.out.println("match caab, c+b -> " + outer.isMatchWithPlus("caab", "c+b"));
        System.out.println("match caab, c+b -> " + outer.isMatchWithPlus("cab", "c+b"));
    }
}
