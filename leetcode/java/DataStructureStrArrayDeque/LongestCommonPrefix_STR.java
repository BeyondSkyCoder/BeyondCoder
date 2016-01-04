package DataStructureStrArrayDeque;

public class LongestCommonPrefix_STR {
    /*
      Write a function to find the longest common prefix string amongst an array of strings.
     */
    // Algorithm: scan each char from string list
    //          return substring (0, end) if any terminates

    public String longestCommonPrefix(String[] strs) {
        int end = 0;
        int len;
        char c = ' ';
        String str = "";
        if (strs == null || strs.length == 0)
            return str;

        while (true) {

            for (int i = 0; i < strs.length; i++) {

                // close if any string terminates
                // otherwise, record the first char and compare with the rest of the list
                // Note, order is important
                if (end > strs[i].length() - 1) {
                    return strs[0].substring(0, end);
                } else {
                    if (i == 0) {
                        c = strs[i].charAt(end);
                    } else {
                        if (c != strs[i].charAt(end)) {
                            return strs[0].substring(0, end);
                        }
                    }
                }
            }
            end++;
        }
    }
}