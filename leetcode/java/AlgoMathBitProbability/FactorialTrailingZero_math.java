/*
 * Given an integer n, return the number of trailing zeroes in n!
 * Note: Your solution should be in polynomial time complexity.
 * 
 * O（logn）解法：
一个更聪明的解法是：考虑n!的质数因子。后缀0总是由质因子2和质因子5相乘得来的。如果我们可以计数2和5的个数，问题就解决了。考虑下面的例子：

n = 5: 5!的质因子中 (2 * 2 * 2 * 3 * 5)包含一个5和三个2。因而后缀0的个数是1。

n = 11: 11!的质因子中(2^8 * 3^4 * 5^2 * 7)包含两个5和三个2。于是后缀0的个数就是2。

n!的质因子中所有5的个数呢？一个简单的方法是计算floor(n/5)。
首先对n÷5，移除所有的单个5，然后÷25，移除额外的5，

 */
public class FactorialTrailingZero_math {
    public int trailingZeroes(int n) {
        if (n < 1) return 0;
        int count = 0;
        for (; n > 0; n /= 5) {
            count += n / 5;
        }
        return count;
    }
}
