package max.learning.algorithms;

/**
 * <a href="https://leetcode.com/problems/move-zeroes/"/>
 *
 * Solution with description:
 * <a href="https://leetcode.com/problems/move-zeroes/discuss/2170514/Java-O(n)-time-complexity-without-swapping-elements.-100-faster"/>
 *
 * Given an integer array nums, move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 */
class MoveZeroes {
    public void moveZeroes(int[] nums) {        
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > cnt) {
                    nums[cnt] = nums[i];                    
                }
                cnt++;
            }
        }
        while (cnt < nums.length) {
            nums[cnt++] = 0;
        }
    }
}