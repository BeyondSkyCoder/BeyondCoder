package AlgoBackTracking;

import java.util.ArrayList;
import java.util.List;

public class ReturnIPAddress_BT {
    /**
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * For example:
     * Given "25525511135",
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */
    // Algorithm BackTracking with subgroup partition, subject to partition rules
    //      Loop is increment scan
    //      Note: IP address has set of rules: 4 segments, up to 3 digits for each segment, no "00"

    private static int IP_ADDRESS_SEGMENT = 4;
    private static int IP_ADDRESS_SEGMENT_LEN = 3;  // up to 3 digits

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        int n = s.length();
        if (n < 4 || n > 12) return res;  // ip address must have 4 segments, 12 digits at most

        List<String> levelList = new ArrayList<>();
        restoreIpAddressesHelper(s, res, levelList, 0);
        return res;
    }

    public void restoreIpAddressesHelper(String s, List<String> result, List<String> levelList, int pos) {
        if (levelList.size() == IP_ADDRESS_SEGMENT) {
            if (pos != s.length())  // still digits left, invalid, return
                return;

            // for the result
            StringBuilder s1 = new StringBuilder();
            for (String l : levelList) {
                s1.append(l);
                s1.append(".");
            }
            s1.setLength(s1.length() - 1);  // remove the last .
            result.add(s1.toString());
            return;
        }

        // parse each segment, up to three digits
        for (int i = pos; i < pos + IP_ADDRESS_SEGMENT_LEN && i < s.length(); i++) {

            String t = s.substring(pos, i + 1);

            if (!isValidIpAddress(t)) return;

            levelList.add(t);
            restoreIpAddressesHelper(s, result, levelList, i + 1);
            levelList.remove(levelList.size() - 1);
        }
    }

    public boolean isValidIpAddress(String t) {
        if (t.charAt(0) == '0') {
            return t.equals("0");   // eliminate case of "00"
        }
        int val = Integer.valueOf(t);
        return (val >= 0 && val <= 255);
    }

    public static void main(String[] arg) {
        ReturnIPAddress_BT outer = new ReturnIPAddress_BT();
        String s = "010010";
        System.out.println(outer.restoreIpAddresses(s));
    }
}

