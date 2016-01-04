package AlgoMathBitProbability;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
public class DivideTwoInterger_bit {

    public int divideTLE(int dividend, int divisor) {
        boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long longdividend = Math.abs(dividend);
        long longdivisor = Math.abs(divisor);
        if (longdivisor > longdividend)
            return 0;

        long sum = 0;
        long pow = 0;
        int result = 0;
        while (longdividend > longdivisor) {
            pow = 1;
            sum = longdivisor;

            // find maximum sum * pow allowed
            while (sum + sum < longdividend) {
                sum += sum;
                pow += pow;
            }
            longdividend -= sum;
            result += pow;

        }
        return neg ? -result : result;
    }

    public int divide(int dividend, int divisor) {
        boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        long longdividend = Math.abs(dividend);
        long longdivisor = Math.abs(divisor);
        if (longdivisor > longdividend)
            return 0;


        // find the highest left shift bit
        int shift = 0;
        while (longdividend > (longdivisor << shift)) shift++;

        int results = 0;
        for (; shift > 0; shift--) {
            if (longdividend > (longdivisor << shift)) {
                results += 1 << shift;
                longdividend -= longdivisor << shift;
            }
        }

        return neg ? -results : results;
    }
}
