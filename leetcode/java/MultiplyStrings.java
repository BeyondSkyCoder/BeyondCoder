package leetcode;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.

 Note: The numbers can be arbitrarily large and are non-negative.
 */

// Algorithm: Math, num1[i] * num2[j] goes to res[i+j], hand carry separately
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        // string LSB is digital MSB, reverse it
        String nsNum1 = new StringBuilder(num1).reverse().toString();
        String nsNum2 = new StringBuilder(num2).reverse().toString();

        // multiple each digits
        int n1 = nsNum1.length();
        int n2 = nsNum2.length();
        int [] d = new int[n1+n2];
        for (int i = 0; i < n1; i++) {
            int d1 = nsNum1.charAt(i) - '0';
            for (int j = 0; j < n2; j++) {
                int d2 = nsNum2.charAt(j) - '0';
                // System.out.println("d1 is " + d1 + " d2 is " + d2);
                d[i + j] += d1 * d2;
            }
        }

        // add carry
        StringBuilder res = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < n1 + n2; i++) {
            d[i] += carry;
            carry = d[i] / 10;
            d[i] %= 10;
        }

        // remove leading 0
        boolean digitStart = false;
        for (int i = n1 + n2 - 1; i >= 0; i--) {
            if (d[i] != 0) digitStart = true;
            if (d[i] == 0 && digitStart == false) {
                continue;
            }
            res.append(d[i]);
        }
        if (!digitStart) res.append(0); // if all 0, return 0

        return res.toString();
    }

    public static void main(String [] arg) {
        MultiplyStrings outer = new MultiplyStrings();
        String a1 = "0", b1 = "0";
        System.out.println(outer.multiply(a1, b1));
    }
}
