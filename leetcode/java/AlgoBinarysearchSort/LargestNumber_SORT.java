package AlgoBinarysearchSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber_SORT {
    /*
    Given a list of non negative integers, arrange them such that they form the largest number.

    For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

    Note: The result may be very large, so you need to return a string instead of an integer.
     */

    public String largestNumber(int[] nums) {
        if (nums == null) {
            return null;
        }

        List<Integer> numList = new ArrayList<>();
        for (int n1 : nums) {
            numList.add(n1);
        }

        // Need to set the Comparator right
        Collections.sort(numList,
                new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        String s1 = "" + o1 + o2;
                        String s2 = "" + o2 + o1;
                        return s2.compareTo(s1);
                    }
                });

        StringBuilder sb = new StringBuilder();
        for (Integer ii : numList) {
            sb.append(ii);
        }

        if (sb.charAt(0) == '0') {  // empty case
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber_SORT outer = new LargestNumber_SORT();
        int[] input = new int[]{20, 1, 2, 4};
        System.out.println(outer.largestNumber(input));
    }

}
