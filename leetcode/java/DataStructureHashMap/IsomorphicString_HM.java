package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString_HM {
    /**
     * Given two strings s and t, determine if they are isomorphic.
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     * All occurrences of a character must be replaced with another character while
     * preserving the order of characters. No two characters may map to the same
     * character but a character may map to itself.
     *
     * For example,
     * Given "egg", "add", return true.
     * Given "foo", "bar", return false.
     * Given "paper", "title", return true.
     * Note:
     * You may assume both s and t have the same length.
     */

    // Algorithm: bi-directional HashMap[char] -> char
    //   If either mapping failure, return false

    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) return false;

        Map<Character, Character> hm1 = new HashMap<>();
        Map<Character, Character> hm2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (!hm1.containsKey(c1)) {
                hm1.put(c1, c2);
            } else {
                if (c2 != hm1.get(c1))
                    return false;
            }
            if (!hm2.containsKey(c2)) {
                hm2.put(c2, c1);
            } else {
                if (c1 != hm2.get(c2))
                    return false;
            }
        }
        return true;
    }
}
