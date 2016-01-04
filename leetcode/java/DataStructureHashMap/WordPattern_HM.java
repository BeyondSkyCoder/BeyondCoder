package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class WordPattern_HM {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.
     * Here follow means a full match, such that there is a bijection
     * between a letter in pattern and a substring in str.
     *
     * Examples:
         * pattern = "abba", str = "dog cat cat dog" should return true.
         * pattern = "abba", str = "dog cat cat fish" should return false.
         * pattern = "aaaa", str = "dog cat cat dog" should return false.
         * pattern = "abba", str = "dog dog dog dog" should return false.
     * Notes: You may assume pattern contains only lowercase letters, and str contains
     * lowercase letters separated by a single space.
     */

    // Algorithm: bi-directional HashMap
    //          HashMap[pattern_char] -> str_element
    //          HashMap[str_element] -> pattern_char

    public boolean wordPattern(String pattern, String str) {
        if (str == null) return (pattern == null);

        Map<Character, String> hm1 = new HashMap<>();
        Map<String, Character> hm2 = new HashMap<>();

        String[] token = str.split(" ");
        if (pattern.length() != token.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!hm1.containsKey(c)) {
                if (hm2.containsKey(token[i])) {
                    return false;
                }

                // add the relationship to two directional HashMap
                hm1.put(c, token[i]);
                hm2.put(token[i], c);
            } else {
                if (!hm1.get(c).equals(token[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
