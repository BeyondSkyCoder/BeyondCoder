package AlgoMathBitProbability;

public class AddBinary_Math {
    /*
        Given two binary strings, return their sum (also a binary string).

        For example,
        a = "11"
        b = "1"
        Return "100".
     */
    // Algorithm: bit by bit addition, update carry bit and run to the end

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        int carry = 0;
        int lenA = a.length();
        int lenB = b.length();

        while (i < lenA && i < lenB) {
            int sum = a.charAt(lenA - 1 - i) - '0' + b.charAt(lenB - 1 - i) - '0' + carry;
            if (sum >= 2) {
                carry = 1;
                sum %= 2;
            } else {
                carry = 0;
            }
            res.append(String.valueOf(sum));
            i++;
        }

        if (i == lenA) {
            a = b;
            lenA = a.length();
        }

        while (i < lenA) {
            int sum = a.charAt(lenA - 1 - i) - '0' + carry;
            if (sum >= 2) {
                carry = 1;
                sum %= 2;
            } else {
                carry = 0;
            }
            res.append(String.valueOf(sum));
            i++;
        }

        // don't forget the last carry bit
        if (carry != 0) {
            res.append(String.valueOf(carry));
        }

        return res.reverse().toString();
    }
}
