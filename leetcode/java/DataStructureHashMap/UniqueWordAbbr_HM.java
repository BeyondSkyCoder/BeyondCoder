package DataStructureHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbr_HM {
    /**
     * An abbreviation of a word follows the form <first letter><number><last letter>.
     * Below are some examples of word abbreviations:

     * a) it                      --> it    (no abbreviation)
     * b) d|o|g                   --> d1g
     * c) i|nternationalizatio|n  --> i18n
     * d) l|ocalizatio|n          --> l10n

     * Assume you have a dictionary and given a word, find whether its abbreviation is unique
     * in the dictionary. A word's abbreviation is unique if no other word from the dictionary
     * has the same abbreviation.
     *
     * Example:
     * Given dictionary = [ "deer", "door", "cake", "card" ]
     * isUnique("dear") -> false
     * isUnique("cart") -> true
     * isUnique("cane") -> false
     * isUnique("make") -> true
     *
     * Note: any string with less than 3 char is true
     * Note, the requirement is not very clear. Below testcase has to pass
     *   Input Directionary: ["dog"]
     *   Check:
     *      isUnique("dig"),isUnique("dug"),isUnique("dag"),isUnique("dog"),isUnique("doge")
     *      [false,false,false,true,true]. The "dog" returns true as it matches the original string(no dupplicate)
     */

    // Algorithm: HashMap[i18n_signature] -> Set(origin_string_on_dictionary)

    Map<String, Set<String>> hm = new HashMap<>();

    // part 1: build the Hashmap from dictionary
    public void ValidWordAbbr(String[] dictionary) {
        for (String s : dictionary) {
            int len = s.length();
            String key = "";
            if (len <= 2) {
                continue;
            } else {
                // first_char+no_in_middle+last_char
                key += s.substring(0, 1) + String.valueOf(len - 2) + s.substring(len - 1, len);
            }

            if (!hm.containsKey(key)) {
                Set<String> hs = new HashSet<>();
                hs.add(s);
                hm.put(key, hs);
            } else {
                hm.get(key).add(s);
            }
        }
    }

    // part 2: check input word against Hashmap
    public boolean isUnique(String word) {
        if (word == null || word.length() <= 2) {
            return true;
        }

        String key = "";
        int len = word.length();
        key += word.substring(0, 1) + String.valueOf(len - 2) + word.substring(len - 1, len);

        if (!hm.containsKey(key)) {
            return true;
        } else {
            Set<String> hs = hm.get(key);

            if (hs.size() > 1) {
                // more than one, there is dup, return false
                return false;
            } else {
                // only one, check exact match
                return hs.contains(word);
            }
        }
    }
}
