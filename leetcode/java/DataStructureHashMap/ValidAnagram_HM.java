package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram_HM {
    /*
     * Given two strings s and t, write a function to determine if t is an anagram of s.
       For example,
        s = "anagram", t = "nagaram", return true.
        s = "rat", t = "car", return false.

        Note:
        You may assume the string contains only lowercase alphabets.
     */

    // Algorithm: HashMap[str_char] -> count
    //      two scan: one to build HashMap from s, the second to check t against the HashMap

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (hm.containsKey(c))
                hm.put(c, hm.get(c) + 1);
            else
                hm.put(c, 1);
        }

        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);

            // the >=1 is critical as there could be duplicated chars
            if (hm.containsKey(c) && hm.get(c) >= 1)
                hm.put(c, hm.get(c) - 1);
            else
                return false;
        }

        return true;
    }
}
