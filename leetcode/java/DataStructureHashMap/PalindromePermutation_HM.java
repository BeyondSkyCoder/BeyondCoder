package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation_HM {
    /**
     * Given a string, determine if a permutation of the string could form a palindrome.
     *
     * For example,
     * "code" -> False, "aab" -> True, "carerac" -> True.
     */

    // Algorithm, HashMap[str_char] -> count.
    //  If count == 2, remove it
    //  final hash should have 0 or 1 HM left with (palindrome string could have an old char at the middle)

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.remove(c);
            }
        }

        return (hm.size() <= 1);
    }
}
