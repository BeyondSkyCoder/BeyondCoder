package DataStructureHashMap;

import java.util.*;

public class GroupShiftedString_HM {
    /**
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
     * <p>
     * "abc" -> "bcd" -> ... -> "xyz"
     * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
     * <p>
     * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
     * Return:
     * <p>
     * [
     * ["abc","bcd","xyz"],
     * ["az","ba"],
     * ["acef"],
     * ["a","z"]
     * ]
     */

    /* Algorithm:
     *     HashMap[string_normalized_to_a] -> subgroup
        e.g     bcd -> abc, yza -> abc
     */

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        if (strings == null || strings.length == 0) return result;

        Map<String, List<String>> hm = new HashMap<>();
        List<String> subgroupList;

        for (String s : strings) {
            String key = "";        // build key for each string, normalized to 'a'
            int offset = s.charAt(0) - 'a';
            for (int i = 0; i < s.length(); i++) {
                char nc = (char) (s.charAt(i) - offset);
                if (nc < 'a') nc += 26;
                key += nc;
            }

            // add the new str to its subgroupList in hash
            if (!hm.containsKey(key)) {
                subgroupList = new ArrayList<>();

                subgroupList.add(s);
                hm.put(key, subgroupList);
            } else {
                subgroupList = hm.get(key);
                subgroupList.add(s);
            }
        }

        // shellSort each subgroup per requirement
        for (String s : hm.keySet()) {
            subgroupList = hm.get(s);
            Collections.sort(subgroupList);
            result.add(subgroupList);
        }

        return result;
    }
}
