package topkfrequent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> counters = new HashMap<>();
        for (Integer num : nums) {
            int count = counters.getOrDefault(num,0);
            counters.put(num,count+1);
        }

        TreeMap<Integer, HashSet<Integer>> countKeys = new TreeMap<>();
        for (Map.Entry<Integer,Integer> numCount : counters.entrySet()) {
           HashSet<Integer> existing = countKeys.getOrDefault(numCount.getValue(),new HashSet<>());
           existing.add(numCount.getKey());
           countKeys.put(numCount.getValue(),existing);
        }

        int [] retValue = new int[k];
        for (int j = 0;j<k;) {
            for (Integer lastValue : countKeys.lastEntry().getValue()) {
                if (j < k) {
                    retValue[j] = lastValue;
                    ++j;
                }else {
                    break;
                }
            }

            countKeys.remove(countKeys.lastKey());
        }

        return retValue;
    }
}
