package AlgoMathBitProbability;

public class AddDigits_math {
    /*
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

        For example:

        Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

        more complex math theory: https://en.wikipedia.org/wiki/Digital_root
     */
    // Algorithm: use / and %

    public int addDigits(int num) {
        int res = num;
        while (res > 9) {
            num = res;
            res = 0;
            while (num / 10 > 0) {
                res += num % 10;
                num /= 10;
            }
            res += num % 10;    // last digit
        }

        return res;
    }
}
