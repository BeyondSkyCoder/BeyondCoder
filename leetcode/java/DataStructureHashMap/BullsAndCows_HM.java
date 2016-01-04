package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class BullsAndCows_HM {
    /**
     * You are playing the following Bulls and Cows game with your friend:
     * You write a 4-digit secret number and ask your friend to guess it.
     * Each time your friend guesses a number, you give a hint.
     * The hint tells your friend how many digits are in the correct positions
     * (called "bulls") and how many digits are in the wrong positions
     * (called "cows"). Your friend will use those hints to find out the secret number.
     *
     * For example:
     *  Secret number:  "1807"
     *  Friend's guess: "7810"
     *  Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
     *
     * Write a function to return a hint according to the secret number and friend's guess,
     * use A to indicate the bulls and B to indicate the cows.
     * In the above example, your function should return "1A3B".
     *
     * Please note that both secret number and friend's guess may contain duplicate digits, for example:
     *  Secret number:  "1123"
     *  Friend's guess: "0111"
     * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
     * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
     */

    // Algorithm: HashMap[str_char] -> count
    //      three scans to build hash, find A and B cases

    public String getHint(String secret, String guess) {

        // I, record all secret chars into hashmap
        Map<Character, Integer> hm = new HashMap<>();
        for (Character c : secret.toCharArray()) {
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.put(c, hm.get(c) + 1);
            }
        }

        // II. scan same position for two strings to find char match
        //      if match, reduce counter and remove counter == 0
        int A = 0;
        for (int i = 0; i < secret.length(); i++) {
            Character c1 = secret.charAt(i);
            Character c2 = guess.charAt(i);

            if (c1 == c2) {
                A++;
                hm.put(c1, hm.get(c1) - 1);
                if (hm.get(c1) == 0) {
                    hm.remove(c1);
                }
            }
        }

        // III, scan same position for two string to find contained+unmatch
        //      if match, reduce counter and remove counter == 0
        //      (not contained in hashmap does not count to B)
        int B = 0;
        for (int i = 0; i < secret.length(); i++) {
            Character c1 = secret.charAt(i);
            Character c2 = guess.charAt(i);

            if (c1 == c2 || !hm.containsKey(c2)) {
                // skip those matching case or not in hashmap
                continue;
            }

            B++;
            hm.put(c2, hm.get(c2) - 1);
            if (hm.get(c2) == 0) {
                hm.remove(c2);
            }
        }

        return (String.valueOf(A) + 'A' + String.valueOf(B) + 'B');
    }
}
