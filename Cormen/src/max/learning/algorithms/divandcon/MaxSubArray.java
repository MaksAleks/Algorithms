package max.learning.algorithms.divandcon;

public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        var res = maxSubArray.findMaxSubArray(arr, 0, arr.length);
        System.out.println(res);
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
