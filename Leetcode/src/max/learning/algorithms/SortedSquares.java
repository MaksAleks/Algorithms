package max.learning.algorithms;

import java.util.Arrays;

class SortedSquares {
    public int[] trivialSolution(int[] nums) {
        return Arrays.stream(nums)
                .map(i -> i * i)
                .sorted()
                .toArray();
    }

    public int[] trivialSolutionWithoutStreams(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = i * i;
        }
        Arrays.sort(res);
        return res;
    }

    /**
     * we can use the fact that `nums` is already sorted
     * @param nums
     * @return sorted array of squares
     */
    public int[] fastSolutionUsingMergeAlgorithm(int[] nums) {
        // init array of squares
        int[] squares = new int[nums.length];
        int i = 0;
        for (; i < nums.length; i++) {
            squares[i] = nums[i] * nums[i];
        }

        // created result array
        int[] res = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        i = r;
        while (l <= r) {
            if (squares[r] >= squares[l]) {
                res[i--] = squares[r--];
            }
            else {
                res[i--] = squares[l++];
            }
        }
        return res;
    }
}