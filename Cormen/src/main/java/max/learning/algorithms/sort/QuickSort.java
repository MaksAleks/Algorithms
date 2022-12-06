package max.learning.algorithms.sort;

import java.util.Arrays;

public class QuickSort implements Sort{

    int[] arr;

    public static void main(String[] args) {
        var sort = new QuickSort();
        for (int i = 0; i < 1000; i++) {
            sort.test(i);
        }
        System.out.println("Quick sort works fine");
    }

    @Override
    public int[] sort(int[] input) {
        arr = input;
        quickSort(0, arr.length);
        return Arrays.copyOf(arr, arr.length);
    }

    private void quickSort(int p, int r) {
        if (p < r) {
            int q = partition(p, r);
            quickSort(p, q);
            quickSort(q + 1, r);
        }
    }

    private int partition(int p, int r) {
        int pivot = arr[r - 1];
        int i = p - 1;
        for (int j = p; j < r - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        if (i == p - 1) {
            return (p + r) / 2;
        }
        swap(arr, i + 1, r - 1);
        return i + 1;
    }
}
