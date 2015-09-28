package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.

 For example,
 "code" -> False, "aab" -> True, "carerac" -> True.
 */

// Algorithm, should has no more than one char with old occurence(mid one in palindrome string)
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.remove(c);
            }
        }

        return hm.size() <= 1;
    }
}
