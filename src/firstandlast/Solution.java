package firstandlast;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    ArrayList<Integer> combine(int current,ArrayList<Integer> left,ArrayList<Integer> right) {
        int leftMost = current;
        int rightMost = current;

        if (left.isEmpty() == false) {
            leftMost = left.get(0);
        }

        if (right.isEmpty() == false) {
            rightMost = right.get(1);
        }

        return new ArrayList<>(Arrays.asList(leftMost,rightMost));
    }

    ArrayList<Integer> itr(int []nums,int begin,int end,int target) {
        if (begin > end) {
            return new ArrayList<>();
        }else {
            if (begin == end) {
                if (nums[begin] == target) {
                    return new ArrayList<>(Arrays.asList(begin,begin));
                }else {
                    new ArrayList<>();
                }
            }else {
                int mid = begin + (end-begin)/2;
                if (nums[mid] > target) {
                    return itr(nums,begin,mid,target);
                }else {
                    if (nums[mid] < target) {
                        return  itr(nums,mid+1,end,target);
                    }else {
                        ArrayList<Integer> left = itr(nums,begin,mid,target);
                        ArrayList<Integer> right = itr(nums,mid+1,end,target);
                        return combine(mid,left,right);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public int[] searchRange(int[] nums, int target) {
        ArrayList<Integer> sol = itr(nums,0,nums.length-1,target);
        if (sol.isEmpty() == false) {
            int [] retValue = {sol.get(0),sol.get(1)};
            return retValue;
        }else {
            int [] retValye = {-1,-1};
            return retValye;
        }

    }
}
