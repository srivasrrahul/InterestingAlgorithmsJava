package threesumclosest;

import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int s = 0;
        for (int j = 0;j < nums.length-2;++j) {
            for (int k = 0;k<nums.length-1;++k) {
                int pending = target-(nums[j]+nums[k]);
                int retValue1 = Arrays.binarySearch(nums,k+1,nums.length-1,pending);
                if (retValue1 >= 0) {
                    return target;
                }

                int retValue2= Arrays.binarySearch(nums,k+1,nums.length-1,-pending);
                if (retValue2 >=0) {
                    return target;
                }

                int ip1 = -(retValue1+1);
                int ip2 = -(retValue2+1);

                s = getS(nums, target, minDiff, s, j, k, ip1);

                s = getS(nums, target, minDiff, s, j, k, ip2);

                ip1 = -(retValue1+1);
                ip2 = -(retValue2+1);

                s = getS(nums, target, minDiff, s, j, k, ip1);

                s = getS(nums, target, minDiff, s, j, k, ip2);


            }
        }

        return s;
    }

    private int getS(int[] nums, int target, int minDiff, int s, int j, int k, int ip2) {
        if (ip2-1 >= k+1 && ip2+1 <= nums.length) {
            int s2 = Math.abs(target - (nums[ip2] + nums[j]+nums[k]));
            if (s2 < minDiff) {
                s = nums[ip2] + nums[j]+nums[k];
            }
        }
        return s;
    }
}
