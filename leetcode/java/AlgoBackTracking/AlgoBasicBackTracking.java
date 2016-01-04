package AlgoBackTracking;


/*
    RECURSION is very important, and essential, natural for algorimthist
	   find the base case
	   find the relationship from n-1 case to n case

    Some subtypes like backtracking(bruce-force with pruning) become very popular thus independent

    ================
    BackTracking Algorithm Framework has a few variations
        1. original data dup, no dup ? use standard n[i]==n[i-1] to skip dup
        2. resulting must be ordered like non-descending ? -- shellSort first
        3. result can be dup, no dup ? use sublist.contains()
        4. subsequent recursion can go to previous position ? -- set start/limit position
        5. track the current used pool ? visited[] flag vs hashset()
    ================
*/

import java.util.ArrayList;

public class AlgoBasicBackTracking {


    // **See LeetCode example for more details
    // for a string with multiple char, generation all permutations
    // non loop version.
    public static ArrayList<String> getPermutation(String str) {
        ArrayList<String> permutation = new ArrayList<String>();

        if (str == null) {
            return null;
        }

        if (str.length() == 0) {
            // base case
            permutation.add("");
            return permutation;
        }

        char first_ch = str.charAt(0);
        String remainder = str.substring(1);    // remove the first char

        // RECURSION
        ArrayList<String> subset = getPermutation(remainder);
        for (String word : subset) {

            // from n-1 to n, insert one new char from beginning to the end
            for (int i = 0; i < word.length() + 1; i++) {
                String s = util_insertCharAt(word, first_ch, i);
                permutation.add(s);
            }
        }

        return permutation;
    }

    public static String util_insertCharAt(String s, char ch, int index) {
        String start = s.substring(0, index);
        String end = s.substring(index);
        return start + ch + end;
    }

    public static void main(String[] args) {

    }

}
