package leetcode;

import java.util.*;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

 "abc" -> "bcd" -> ... -> "xyz"
 Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

 For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 Return:

 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]
 */

// inspect each string to find offset pattern, use that as hash key

public class GroupShiftedString {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();

        if (strings == null || strings.length == 0) return res;
        Map<String, List<String>> hm = new HashMap<>();

        for (String s : strings) {
            String key = "";        // key is the root word for each string shift group
            int offset = s.charAt(0) - 'a';
            for (int i = 0; i < s.length(); i++) {
                char nc = (char)(s.charAt(i) - offset);
                if (nc < 'a') nc += 26;
                key += nc;
            }

            if (!hm.containsKey(key)) {
                // create new list, add to hashmap
                List<String> ns = new ArrayList<>();
                hm.put(key, ns);
            }
            // hashmap has the key, whether newly created or existing, add the s to the list
            hm.get(key).add(s);
        }

        List<String> tmp;
        for (String s: hm.keySet()) {
            tmp = hm.get(s);
            Collections.sort(tmp);  // requirement is to sort each sublist
            res.add(tmp);
        }

        return res;
    }
}
