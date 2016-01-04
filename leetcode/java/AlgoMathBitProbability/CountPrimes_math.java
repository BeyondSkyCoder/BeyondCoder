/**
 * Description:
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n.
 * But don't let that name scare you, I promise that the concept is surprisingly simple.
 * <p>
 * if the current number is p, we can always mark off multiples of p starting at p2, then in increments of p: p2 + p, p2 + 2p
 * terminating loop condition can be p < √n
 */
public class CountPrimes_math {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
}
