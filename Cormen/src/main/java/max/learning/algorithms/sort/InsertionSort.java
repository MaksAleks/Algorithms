package max.learning.algorithms.sort;

public class InsertionSort implements Sort {

    public static void main(String[] args) {
        var sort = new InsertionSort();
        for (int i = 0; i < 1000; i++) {
            sort.test(i);
        }
        System.out.println("Insertion sort works fine");
    }

    @Override
    public int[] sort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            // input[0...i] is considered to be sorted
            // we get next element (i + 1)
            // and try to insert it to the sorted part of the array
            int j = i;
            while (j >= 0 && input[j + 1] < input[j]) {
                swap(input, j + 1, j);
                j--;
            }
        }
        return input;
    }
}
