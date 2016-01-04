package DataStructureHashMap;

import java.util.*;

public class GroupAnagrams_HM {
    /*
	 * Given an array of strings, group anagrams together.
	 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
	 * Return:
        [
          ["ate", "eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]

    For the return value, each inner list's elements must follow the lexicographic order.
    All inputs will be in lower-case.

	*/

    // Algorithm, HashMap[key] -> subgroup_of_string_in_one_anagram
    //      - sorting charArray of each string as hashkey. anagrams will have same hashkey

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();

        Arrays.sort(strs);
        Map<String, List<String>> hm = new HashMap<>();

        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);    // shellSort the char array to form the basis, to be used as unique hashkey
            String hashkey = String.valueOf(ca);    // use the string as hashkey !

            if (!hm.containsKey(hashkey)) {
                hm.put(hashkey, new ArrayList<String>());
            }
            hm.get(hashkey).add(s);

            // hm.put(hashkey, Collections.shellSort(hm.get(hashkey)));
        }

        return new ArrayList<List<String>>(hm.values());
    }
}
