package AlgoDynamicProgram;

public class EditDistanceMinOne {

    /*
        find EditDistance of two strings, minimum of insert/delete/substitution
     */

    // Algorithm: DP w/ 2-D O(n*m) memory
    //  initialize ed[i][0] and ed[0][j]
    //  then populate ed[i][j],  upper=>insert, left=>delete, up-left=>substituion

    public int EditDistance(String word1, String word2) {
        int i, j;

        int slen = word1.length();
        int tlen = word2.length();

        if (slen == 0) return (tlen);
        if (tlen == 0) return (slen);

        // 2D matrix
        int[][] ed = new int[slen + 1][tlen + 1];

        for (i = 0; i < slen + 1; i++) ed[i][0] = i;
        for (j = 0; j < tlen + 1; j++) ed[0][j] = j;

        for (i = 1; i < slen + 1; i++) {
            for (j = 1; j < tlen + 1; j++) {

                int insert = ed[i - 1][j] + 1;
                int delete = ed[i][j - 1] + 1;

                // std definition for substitude, 0 for match, 1 for no-match
                int temp = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                int substitution = ed[i - 1][j - 1] + temp;

                // min of (insert, delete, substitute)
                int min = Math.min(insert, delete);
                min = Math.min(min, substitution);

                ed[i][j] = min;
            }
        }

        return ed[slen][tlen];
    }

    // Algorithm: DP is overkilled ! just use two pointers to compare char by char
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();

        // always make sure first len < second len to simplify below logic
        if (m > n) return isOneEditDistance(t, s);

        // return false if length diff is more than 1
        if (n - m > 1) return false;

        // start the game
        int i = 0, shift = n - m; // by here, the shift is either 0 or 1

        // skip all the same chars
        while (i < m && s.charAt(i) == t.charAt(i)) {
            ++i;
        }

        if (i == m) return shift > 0; // if reaches the end of string s, no different so far, check the shift

        // there is a different char in string s
        // if n==m, skip one char in s and t assume that char is modified with ed=1
        // after this one, remaining must all be the same char
        if (shift == 0) i++;

        while (i < m && s.charAt(i) == t.charAt(i + shift)) i++; // use shift to skip one char in t

        return (i == m);
    }

    public static void main(String[] args) {

        EditDistanceMinOne outer = new EditDistanceMinOne();
        System.out.println("ed[1] " + outer.isOneEditDistance("junk", "jun"));

        System.out.println("ed[1] " + outer.EditDistance("junkie", "jun"));
    }
}