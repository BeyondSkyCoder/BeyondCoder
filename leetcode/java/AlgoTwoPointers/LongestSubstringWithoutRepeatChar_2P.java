package AlgoTwoPointers;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatChar_2P {

    /*
        Given a string, find the length of the longest substring without repeating characters.
        For example, the longest substring without repeating letters for "abcabcbb" is "abc",
        which the length is 3.
        For "bbbbb" the longest substring is "b", with the length of 1.
     */

    // Algorithm: two pointers: second pointer scan to compare using HashMap

    public int lengthOfLongestSubstring(String s) {

        char[] sa = s.toCharArray();
        int len = sa.length;
        int i = 0, j = 0;
        int wordstart = 0;
        char ch;
        int maxlen = 0;
        HashMap<Character, Integer> hm1 = new HashMap<>();

        for (i = 0; i < len; i++) {
            hm1.clear();
            hm1.put(sa[i], 1);
            // scan to the end
            for (j = i + 1; j < len; j++) {
                ch = s.charAt(j);
                if (!hm1.containsKey(ch)) {
                    hm1.put(ch, 1);
                } else {
                    break;
                }
            }

            // found unique substring
            if (maxlen < (j - i)) {
                maxlen = j - i;
            }
        }

        return maxlen;
    }
}
