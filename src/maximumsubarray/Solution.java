package maximumsubarray;

public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0]; //check for size here
        int prevSum = nums[0];
        for (int j = 1;j<nums.length;++j) {
            int currentMax = Math.max(nums[j],nums[j]+prevSum);
            maxSum = Math.max(maxSum,currentMax);
            prevSum = currentMax;
        }

        return maxSum;
    }
}
