package DataStructureStrArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecode_STR {
    /**
     * Design an algorithm to encode a list of strings to a string.
     * The encoded string is then sent over the network and is decoded back to
     * the original list of strings.

     * Machine 1 (sender) has the function:
     * string encode(vector<string> strs) {
     *      // ... your code
     *      return encoded_string;
     * }
     * Machine 2 (receiver) has the function:
     * vector<string> decode(string s) {
     *      //... your code
     *      return strs;
     * }
     * So Machine 1 does: string encoded_string = encode(strs);
     * and Machine 2 does: vector<string> strs2 = decode(encoded_string);
     * strs2 in Machine 2 should be the same as strs in Machine 1.

     * Implement the encode and decode methods.
     * Note:
     * The string may contain any possible characters out of 256 valid ascii characters.
     * Your algorithm should be generalized enough to work on any possible characters.
     * Do not use class member/global/static variables to store states.
     * Your encode and decode algorithms should be stateless.
     * Do not rely on any library method such as eval or serialize methods.
     * You should implement your own encode/decode algorithm.
     */

    // Algorithm: encoding with tuple (length+delimiter+string)

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s.length()).append("/").append(s);
        }
        return res.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        int n = s.length();

        // parse tuple by tuple
        while (i < n) {
            int j = i + 1;
            // find delimiter '/'
            while (j < n && s.charAt(j) != '/') j++;
            // j is at delimiter. Front is length and after is string
            int len = Integer.parseInt(s.substring(i, j));
            res.add(s.substring(j + 1, j + 1 + len));
            i = j + len + 1;
        }
        return res;
    }
}
