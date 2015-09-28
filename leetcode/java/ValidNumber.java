package leetcode;

/**
 * Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous.
 You should gather all requirements up front before implementing one.
 */

/* Algorithm:
A string could be divided into these four substrings in the order from left to right:
        s1. Leading whitespaces (optional).
        s2. Plus (+) or minus (–) sign (optional).
        s3. Number.
        s4. Optional trailing whitespaces (optional).

   In S3:
        a number could either be a whole number or a decimal number
        a decimal number could be further divided into three parts:
            a. Integer part
            b. Decimal point
            c. Fractional part
            The integer and fractional parts contain only digits. For example, the number “3.64” has
            integer part (3) and fractional part (64). Both of them are optional, but at least one of
            them must present. For example, a single dot ‘.’ is not a valid number, but “1.”, “.1”, and
            “1.0” are all valid. Please note that “1.” is valid because it implies “1.0”.

    More requirement
        A number could contain an optional exponent part, which is marked by a character ‘e’
        followed by a whole number (exponent). For example, “1e10” is numeric. Modify the
        above code to adapt to this new requirement.
*/

public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null  || s.length() == 0) return false;

        int n = s.length();
        int i = 0;
        boolean isNumeric = false;
        while (i < n && Character.isWhitespace(s.charAt(i))) i++;

        System.out.println("n is " + n + " i is " + i);
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;

        // main body of number
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumeric = true;
        }

        // . must be followed with more digits, if not, previous digits(isNumeric) will be carried over
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
                // System.out.println(" i is " + i);
            }
        }

        // handle case like -.3e6, 2e10 or 1e-20
        if (isNumeric && i < n && s.charAt(i) == 'e') {
            i++;
            isNumeric = false;
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                // System.out.println(" i is " + i);
                i++;
                isNumeric = true;
            }
        }
        while (i < n && Character.isWhitespace(s.charAt(i)) ) i++;
        return isNumeric && i == n;
    }

    public static void main(String[] args) {
        ValidNumber outer = new ValidNumber();
        String s1 = "-.3e6";
        String s2 = " ";
        System.out.println(outer.isNumber(s2));
    }
}
