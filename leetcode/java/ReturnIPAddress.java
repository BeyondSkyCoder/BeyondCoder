package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

public class ReturnIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        int n = s.length();
        if (n < 4 || n > 12) return res;  // ip address must have 4 segments, 12 digits at most

        restoreIpAddressesHelper(s, res, 0, new ArrayList<>());
        return res;
    }

    public void restoreIpAddressesHelper(String s, List<String> res, int pos, List<String> sublist) {
        if (sublist.size() == 4) {
            if (pos != s.length())  // too much digits, invalid, return
                return;

            StringBuilder s1 = new StringBuilder();
            for (String ii : sublist) {
                s1.append(ii);
                s1.append(".");
            }
            s1.setLength(s1.length() - 1);  // remove the last .
            res.add(s1.toString());
            return;
        }

        for (int i = pos; i < pos + 3 && i < s.length(); i++) {
            String t = s.substring(pos, i+1);
            if (isValidIpAddress(t)) {
                    sublist.add(t);
                    restoreIpAddressesHelper(s, res, i + 1, sublist);
                    sublist.remove(sublist.size() - 1); // backtracking

            } else {
                break;
            }
        }
    }

    public boolean isValidIpAddress(String t) {
        if (t.charAt(0) == '0') {
            return t.equals("0");   // eliminate case of "00"
        }
        int val = Integer.valueOf(t);
        return (val >= 0 && val <= 255);
    }

    public static void main(String [] arg) {
        ReturnIPAddress outer = new ReturnIPAddress();
        String s = "010010";
        System.out.println(outer.restoreIpAddresses(s));
    }

}

