package coinchange;

import java.util.Arrays;
import java.util.HashMap;

class DP {
    HashMap<Integer,Integer> cache = new HashMap<>();
    int itr(int []coins,int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < coins[0]) {
            return Integer.MAX_VALUE; //No possibility
        }else {

            if (cache.containsKey(amount)) {
                return cache.get(amount);
            }
            int minOption = Integer.MAX_VALUE;
            for (int c : coins) {
                if (amount >= c) {
                    int pending = itr(coins,amount-c);
                    if (pending != Integer.MAX_VALUE) {
                        int totalCount = pending+1;
                        if (totalCount < minOption) {
                            minOption = totalCount;
                        }
                    }
                }
            }

            cache.put(amount,minOption);
            return minOption;
        }
    }
}
class Solution {
    int itr(int []coins,int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < coins[0]) {
            return Integer.MAX_VALUE; //No possibility
        }else {

            int minOption = Integer.MAX_VALUE;
            for (int c : coins) {
                if (amount >= c) {
                    int pending = itr(coins,amount-c);
                    if (pending != Integer.MAX_VALUE) {
                        int totalCount = pending+1;
                        if (totalCount < minOption) {
                            minOption = totalCount;
                        }
                    }
                }
            }

            return minOption;
        }
    }
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int retValue = new DP().itr(coins,amount);

        if (retValue == Integer.MAX_VALUE) {
            return -1;
        }

        return retValue;
    }
}
