package max.learning.algorithms.dynamic;

import java.util.*;
import java.util.stream.Collectors;

import static max.learning.algorithms.util.Time.logTime;

public class CutRod {

    public static void main(String[] args) {
        int count = 10;
        var cutRod = new CutRod();
        int[] arr = new Random().ints(1, 100)
                .limit(count)
                .sorted()
                .toArray();

        System.out.println("memoized max 40: " + logTime(() -> cutRod.memoized(arr, count, new HashMap<>(1000))));
        System.out.println("--------------");

        int[] memSols = new int[count + 1];
        System.out.println("extended memoized max 40 : " + logTime(() -> cutRod.extendedMemoized(arr, count, memSols)));
        System.out.println("extended memoized solution for 40: " + optimals(memSols, count));
        String memSol = optimals(memSols, count).stream()
                .map(i -> arr[i - 1])
                .map(String::valueOf)
                .collect(Collectors.joining(" + "));
        System.out.println("extended mem solution: " + memSol + " = \n\t\t = " + optimals(memSols, count).stream().mapToInt(i -> arr[i - 1]).sum());
        System.out.println("-------------------\n");

        System.out.println("bottomUp max 40: " + logTime(() -> cutRod.bottomUp(arr, count)));
        System.out.println("--------------");
        int[] solutions = new int[count + 1];
        System.out.println("bottomUp max 40: " + logTime(() -> cutRod.extendedBottomUp(arr, count, solutions)));
        System.out.println("Solution for 40: " + optimals(solutions, count));
        String solution = optimals(solutions, count).stream()
                .map(i -> arr[i - 1])
                .map(String::valueOf)
                .collect(Collectors.joining(" + "));
        System.out.println("Solution: " + solution + " = \n\t\t = " + optimals(solutions, count).stream().mapToInt(i -> arr[i - 1]).sum());
    }

    public static List<Integer> optimals(int[] list, int n) {
        List<Integer> res = new ArrayList<>();
        while (n > 0) {
            int s = list[n];
            res.add(s);
            n -= s;
        }
        return res;
    }

    public long memoized(int[] p, int n, Map<Integer, Long> memo) {
        if (n == 0) {
            return 0;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long q = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            q = Math.max(q, p[i] + memoized(p, n - i - 1, memo));
        }
        memo.put(n, q);
        return q;
    }

    public long extendedMemoized(int[] p, int n, int[] s) {
        if (n == 0) {
            return 0;
        }
        long[] memo = new long[n];
        for (int i = 0; i < n; i++) {
            memo[i] = -1;
        }

        final long r = extendedMemoized(p, n, memo, s);
        return r;
    }

    private long extendedMemoized(int[] p, int n, long[] memo, int[] s) {
        if (n == 0) {
            return 0;
        }
        if (memo[n - 1] >= 0) {
            return memo[n - 1];
        }

        long q = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long opt = p[i] + extendedMemoized(p, n - i - 1, memo, s);
            if (q < opt) {
                q = opt;
                s[n] = i + 1;
            }
        }
        memo[n - 1] = q;
        return q;
    }

    public long bottomUp(int[] p, int n) {
        long[] r = new long[n + 1];
        r[0] = 0;

        for (int i = 0; i < n; i++) {
            long q = Long.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                q = Math.max(q, p[j] + r[i - j]);
            }
            r[i + 1] = q;
        }
        return r[n];
    }

    public long extendedBottomUp(int[] p, int n, int[] list) {
        long[] r = new long[n + 1];
        r[0] = 0;

        for (int i = 0; i < n; i++) {
            long q = Long.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                long optIJ = p[j] + r[i - j];
                if (q < optIJ) {
                    q = optIJ;
                    // here we found that for the length of i + 1
                    // optimal size of its first part will be j + 1
                    //
                    // And the rest part of size (i - j) has its own optimal size: list.get(i - j)
                    //
                    // Knowing that we can get optimal sizes for i + 1 length:
                    //
                    // l = i + 1;
                    // optimals = {}
                    // while (l > 0) {
                    //     s = list.get(l);
                    //     optimals.add(s);
                    //     l = l - s; - new length has its own optimal
                    // }
                    list[i + 1] = j + 1;
                }
            }
            r[i + 1] = q;
        }
        return r[n];
    }
}
