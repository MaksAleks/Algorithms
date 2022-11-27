package max.learning.algorithms.sort;

public class SelectionSort implements Sort {

    public static void main(String[] args) {
        var sort = new SelectionSort();
        for (int i = 0; i < 1000; i++) {
            sort.test(i);
        }
        System.out.println("Selection sort works fine");
    }


    @Override
    public int[] sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            swap(input, i, min);
        }
        return input;
    }
}
