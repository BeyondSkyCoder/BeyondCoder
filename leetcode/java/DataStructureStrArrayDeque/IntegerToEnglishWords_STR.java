package DataStructureStrArrayDeque;

public class IntegerToEnglishWords_STR {
    /*
    Convert a non-negative integer to its english words representation.
    Given input is guaranteed to be less than 231 - 1.

    For example,
    123 -> "One Hundred Twenty Three"
    12345 -> "Twelve Thousand Three Hundred Forty Five"
    1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    */
    // Algorithm: predefine a few boundary arrays, look up them with number decomposition

    final String[] oTo19 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] twentyToNinety = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    final String[] tmb = {"Billion", "Million", "Thousand"};
    final int[] tmb_limit = {9, 6, 3};

    public String numberToWords(int num) {
        String res = "";
        int limit;

        // A. handle Billion and Million and Thousand segments
        for (int i = 0; i < tmb_limit.length; i++) {
            limit = (int) Math.pow(10, tmb_limit[i]);
            if (num >= limit) {
                res += convert(num / limit) + " " + tmb[i] + " ";
                num = num % limit;
            }
        }
        // B. handle the remaining number(below one thousand)
        res += convert(num);
        res = res.trim();

        return res.isEmpty() ? "Zero" : res;
    }

    public String convert(int threedigits) {
        String str = "";
        int a = threedigits / 100;
        int b = threedigits % 100;
        int c = threedigits % 10;

        // handle hundreds
        if (a > 0) {
            str += oTo19[a] + " Hundred";
        }

        // handle two digits
        if (b >= 20) {
            str += " " + twentyToNinety[b / 10];
            if (c != 0)
                str += " " + oTo19[c];
        } else if (b > 0) { // b <= 19
            str += " " + oTo19[b];
        } else if (c > 0) {
            // b == 0
            str += " " + oTo19[c];
        }

        return str.trim();
    }

    public static void main(String[] args) {
        int n = 1009235401;
        IntegerToEnglishWords_STR tt = new IntegerToEnglishWords_STR();
        System.out.println(n + " is " + tt.numberToWords(n));
    }

}
