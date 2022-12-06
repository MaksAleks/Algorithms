package max.learning.algorithms.dynamic;

import max.learning.algorithms.sort.QuickSort;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class CanSum {

    static class Counter {
        long counter = 0;
    }

    public static void main(String[] args) {
        var canSum = new CanSum();
        int target = 3_000_000;
        Integer[] arr = new Integer[]{1, 7};
        var counter = new Counter();
        var cacheCounter = new Counter();
        Instant start = Instant.now();
        boolean can = canSum.canSum(counter, cacheCounter, target, arr, new HashMap<>(100000));
        Instant end = Instant.now();
        System.out.println("can get 300 000 000 from " + Arrays.toString(arr) + ":" + can);
        System.out.println("Time: " + Duration.between(start, end));
        System.out.println("Counter: " + counter.counter);
        System.out.println("Cache counter: " + cacheCounter.counter);
    }

    public boolean canSum(Counter counter, Counter cacheCounter, int targetSum, Integer[] arr, Map<Integer, Boolean> memo) {
        counter.counter++;
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) return false;

        if (memo.containsKey(targetSum)) {
            cacheCounter.counter++;
            return memo.get(targetSum);
        }

        for (int i : arr) {
            int remainder = targetSum - i;
            if (canSum(counter, cacheCounter, remainder, arr, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }
}
