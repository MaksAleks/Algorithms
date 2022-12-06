package max.learning.algorithms.dynamic;

import max.learning.algorithms.util.Time;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

/**
 * Fibonacci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
 *
 * F_i = F_i-1 + F_i-2
 * F_0 = 0
 * F_1 = 1
 */
public class Fib {

    public static void main(String[] args) {
        var fib = new Fib();
        // bruteforce
        System.out.println("Fib bruteforce calculation:");
        for (int i = 44; i < 45; i++) {
            int I = i;
            System.out.printf("fib(%d) = %s\n", i, Time.logTime(() -> fib.fib(I)));
        }
        System.out.println("---------------");
        // memoization
        System.out.println("Fib memoization calculation:");
        Instant start = Instant.now();
        for (int i = 40; i < 2_000_000; i++) {
            int I = i;
            System.out.printf("fib(%d) = %s\n", i, Time.logTime(() -> fib.fibMemo(I, memo(I))));
        }
        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println("-------------");
        System.out.println("Overall time for memoized calculation of fib(n) for n from 40 to 2000:");
        System.out.println("\t" + time);
    }

    private static BigInteger[] memo(int n) {
        BigInteger[] memo = new BigInteger[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = new BigInteger("-1");
        }
        memo[0] = BigInteger.ZERO;
        memo[1] = BigInteger.ONE;
        return memo;
    }

    public BigInteger fib(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        return fib(n - 1).add(fib(n - 2));
    }

    public BigInteger fibMemo(int n, BigInteger[] memo) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        if (memo[n].compareTo(BigInteger.ZERO) > 0) {
            return memo[n];
        }

        var left = fibMemo(n - 2, memo);
        var right = fibMemo(n - 1, memo);
        memo[n] = left.add(right);
        return memo[n];
    }
}
