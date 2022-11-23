package max.learning.algorithms.divandcon;

public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        var res = maxSubArray.findMaxSubArray(arr, 0, arr.length);
        System.out.println("with divide and conquer:" + res);

        System.out.println("with brute force: " + maxSubArray.findMaxSubArrayBruteforce(arr));
        System.out.println("linear: " + maxSubArray.findMaxSubArrayLinear(arr));
    }

    public Result findMaxSubArrayLinear(int[] arr) {
        int left = 0;
        int right = 0;
        long maxSum = arr[left];
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum < 0) {
                left = i + 1;
                right = left;
                sum = 0;
                continue;
            }
            if (maxSum < sum) {
                maxSum = sum;
                right = i;
            }
        }

        return new Result(left, right, maxSum);
    }

    public Result findMaxSubArrayBruteforce(int[] arr) {
        long maxSum = Long.MIN_VALUE;
        int left = -1;
        int right = -1;
        for (int i = 0; i < arr.length; i++) {
            long sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    left = i;
                    right = j;
                }
            }
        }
        return new Result(left, right, maxSum);
    }

    public Result findMaxSubArray(int[] arr, int l, int h) {
        int m = (l + h) / 2;
        if (l == h - 1) {
            return new Result(l, h, arr[l]);
        }
        var left = findMaxSubArray(arr, l, m);
        var right = findMaxSubArray(arr, m, h);
        var cross = findMaxCrossingArray(arr, l, m, h);

        if (left.sum > right.sum && left.sum > cross.sum) {
            return left;
        } else if (right.sum > cross.sum) {
            return right;
        } else {
            return cross;
        }
    }

    /*
     * time complexity is O(n) (actually it's theta(n))
     */
    private Result findMaxCrossingArray(int[] arr, int l, int m, int h) {
        long leftSum = Integer.MIN_VALUE;
        long sum = 0;
        int maxLeft = m;
        for (int i = m; i >= l; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        long rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = m + 1;
        for (int i = m + 1; i < h; i++) {
            sum += arr[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }



    public record Result(
            int left, int right, long sum
    ) {}
}
