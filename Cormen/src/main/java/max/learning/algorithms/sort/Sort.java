package max.learning.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

public interface Sort {

    default void test(int i) {
        int[] arr = new Random().ints(-500, 500)
                .limit(1000)
                .toArray();

        int[] copy1 = Arrays.copyOf(arr, arr.length);
        int[] copy2 = Arrays.copyOf(arr, arr.length);

        Arrays.sort(copy2);
        if (!Arrays.equals(sort(copy1), copy2)) {
            System.err.println("Error: " + i);
            System.out.println("Array: " + Arrays.toString(arr));
        };
    }

    int[] sort(int[] input);

    default void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
