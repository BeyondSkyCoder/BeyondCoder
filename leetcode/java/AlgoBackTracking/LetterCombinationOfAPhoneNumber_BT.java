package AlgoBackTracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfAPhoneNumber_BT {

    /*
        Given a digit string, return all possible letter combinations that the number could represent.

        A mapping of digit to letters (just like on the telephone buttons) is given below.

        Input:Digit string "23"
        Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        Note:
        Although the above answer is in lexicographical order, your answer could be in any order you want.
     */

    // Algorithm: BackTracking with subgroups
    //      Note: each position combination is determined by the digits[pos]->"xxx" 3-4 key chars group

    //  from left to right: whenever a letter changes, its right neighbor goes through all of its values before the
    //		original letter changes again.
    //  from right to left: Conversely whenever a letter resets t its low value, its left neighbor increases to the next value.

    public List<String> letterCombinations(String digits) {
        /* map 0, 1, 2, ... 9 on phone keypad */
        String[] keyMap = new String[]{null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;

        StringBuilder sb = new StringBuilder();
        letterCombinationsHelper(keyMap, digits, res, sb, 0);
        return res;
    }

    public static void letterCombinationsHelper(String[] km, String digits, List<String> results, StringBuilder s2, int pos) {
        if (digits.length() == pos) {
            results.add(s2.toString());
            return;
        }

        String key = km[digits.charAt(pos) - '0'];    // small optimization: use '2', and remove the null, null from keyMap

        for (int i = 0; i < key.length(); i++) {    // full scan the key subgroup

            s2.append(key.charAt(i));
            letterCombinationsHelper(km, digits, results, s2, pos + 1);
            s2.deleteCharAt(s2.length() - 1);
        }
    }

}
