package DataStructureStrArrayDeque;

public class CountAndSay_STR {

	/*
     * The count-and-say sequence is the sequence of integers beginning as follows:
		1, 11, 21, 1211, 111221, ...
		
		1 is read off as "one 1" or 11.
		11 is read off as "two 1s" or 21.
		21 is read off as "one 2, then one 1" or 1211.
		
		Given an integer n, generate the nth sequence.
		Note: The sequence of integers will be represented as a string. 
	 */

    // Algorithm: recursion, each one use two pointers algorithm to count duplicates

    public static String countAndSay(int n) {

        if (n == 1) {
            return "1";
        }

        String s = countAndSay(n - 1);

        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);

            int num = 1;
            // skip all duplicate chars
            while (i + 1 < s.length() && s.charAt(i + 1) == ch) {
                i++;
                num++;
            }
            // there are j number of charAt(i)
            res.append((char)(num + '0')).append(ch);

            // move to next char
            i++;
        }
        return res.toString();
    }

}
