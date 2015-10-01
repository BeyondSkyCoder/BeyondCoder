package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class StrobogrammaticNumber_I_II {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Write a function to determine if a number is strobogrammatic. The number is represented as a string.

     For example, the numbers "69", "88", and "818" are all strobogrammatic.
     */

    // Algorithm: binary search, two pointer
    public boolean isStrobogrammatic(String num) {
        if (num.length() == 1) {
            char c = num.charAt(0);
            return (c == '8' || c == '1' || c == '0');
        }

        int left = 0;
        int right = num.length() - 1;

        while (left <= right) {
            if ((num.charAt(left) == '6' && num.charAt(right) == '9') ||
                    (num.charAt(left) == '9' && num.charAt(right) == '6') ||
                    (num.charAt(left) == '8' && num.charAt(right) == '8') ||
                    (num.charAt(left) == '1' && num.charAt(right) == '1') ||
                    (num.charAt(left) == '0' && num.charAt(right) == '0')) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].
     */
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<String>();

        String base = "0168";

        return res;
    }
}
