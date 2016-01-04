package AlgoMathBitProbability;

public class IntegerToRoman_math {
    /*
     * Given an integer, convert it to a roman numeral.

        Input is guaranteed to be within the range from 1 to 3999.
     */
    // Algorithm: roman has its own boundaries, which map to certain symbol

    public static String intToRoman(int num) {
        final int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String s = "";

        for (int i = 0; num != 0; i++) {
            while (num >= value[i]) {
                num -= value[i];
                s += symbol[i];
            }
        }
        return s;
    }

    public static void main(String[] args) {
        int i = 94;
        System.out.println(i + "to Roman is " + intToRoman(i));
    }

}
