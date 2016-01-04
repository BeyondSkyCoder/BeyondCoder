package AlgoBackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipGameI_II_BT {
    /**
     * I. You are playing the following Flip Game with your friend: Given a string that
     * contains only these two characters: + and -, you and your friend take turns to flip
     * two consecutive "++" into "--". The game ends when a person can no longer make a move
     * and therefore the other person will be the winner.
     * Write a function to compute all possible states of the string after one valid move.
     * For example, given s = "++++", after one move, it may become one of the following states:
     * [
     * "--++",
     * "+--+",
     * "++--"
     * ]
     * If there is no valid move, return an empty list [].
     */

    // Algorithm: String
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();

        if (s == null || s.length() == 0) return res;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {

                // flip ++ to --
                String tmp = s.substring(0, i - 1) + "--" + s.substring(i + 1);
                res.add(tmp);
            }
        }
        return res;
    }

    /*
     * II. follow I, Write a function to determine if the starting player can guarantee a win.
     * For example, given s = "++++", return true. The starting player can guarantee a win by flipping
     * the middle "++" to become "+--+".
     *  Follow up:
     *  Derive your algorithm's runtime complexity.
     */

    // Algorithm: BackTracking: scan string, try all possible conversion to determine if winnable
    //   in each recursion, the loop starts from beginning of the new converted string
    //   so it's guaranteed to finish
    //   as one optimization, use hash to record string-win pattern to reduce the duplicate computation globally

    public boolean canWin(String s) {
        if (s == null || s.length() < 2)
            return false;

        Map<String, Boolean> hm = new HashMap<>();
        return canWinHelper(s, hm);
    }

    public boolean canWinHelper(String s, Map<String, Boolean> hm) {
        if (hm.containsKey(s)) {
            return hm.get(s);
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                // flip this one, follow up with DFS
                String tmp = s.substring(0, i) + "--" + s.substring(i + 2);

                if (!canWinHelper(tmp, hm)) {
                    hm.put(s, true);
                    return true;
                }

                // BackTracking, to flip next one
            }
        }

        // in this round, since next person could win(above early return), this person will loose, return false
        hm.put(s, false);
        return false;
    }
}
