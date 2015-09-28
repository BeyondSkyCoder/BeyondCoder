package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class LargestNumber_sort {
    public String largestNumber(int[] nums) {

        if (nums == null) {
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int n1 : nums) {
            list.add(n1);
        }

        Collections.sort(list,
                new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                String s1 = "" + o1 + o2;
                String s2 = "" + o2 + o1;

                return s2.compareTo(s1);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Integer ii : list) {
            System.out.println(ii);
            sb.append(ii);
        }

        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber_sort outer = new LargestNumber_sort();
        int[] input = new int[] { 20, 1, 2, 4 };
        System.out.println(outer.largestNumber(input));
    }

}
