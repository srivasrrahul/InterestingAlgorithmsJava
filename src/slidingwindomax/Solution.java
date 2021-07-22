package slidingwindomax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        ArrayList<Integer> retValue = new ArrayList<>();
        TreeMap<Integer,Integer> valCounts = new TreeMap<>();

        for (int j = 0;j<k;++j) {
            int counter = valCounts.getOrDefault(nums[j],0);
            valCounts.put(nums[j],counter+1);
        }

        for (int j = k;j<nums.length;++j) {
            int newElement = nums[j];
            int old = nums[j-k];

            retValue.add(valCounts.lastEntry().getKey());
            int count = valCounts.get(old);
            if (count == 1) {
                valCounts.remove(old);
            }else {
                valCounts.put(old,count-1);
            }

            int newCount = valCounts.getOrDefault(newElement,0);
            valCounts.put(newElement,newCount+1);
        }

        retValue.add(valCounts.lastKey());
        int [] arr = retValue.stream().mapToInt(Integer::intValue).toArray();
        return arr;
        //return retValue.toArray(new int[][]{new int[retValue.size()]});

    }

    public static void main(String[] args) {
        int [] arr = new int[]{1,3,-1,-3,5,3,6,7,1,3,-1,-3,5,3};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(arr,3)));
    }
}
