package besttimetobuysellstock2;

import java.util.*;

class State {
    private int index;
    private int buyPrice;

    public State(int index, int buyPrice) {
        this.index = index;
        this.buyPrice = buyPrice;
    }

    public int getIndex() {
        return index;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return index == state.index && buyPrice == state.buyPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, buyPrice);
    }
}
class DP {
    HashMap<State,Integer> cache = new HashMap<>();
    int max(int []prices,int j,int accumulatedProfit,int buyPrice) {
        if (j == prices.length-1) {
            if (prices[j] > buyPrice) {
                return accumulatedProfit + (prices[j]-buyPrice);
            }else {

                return accumulatedProfit;
            }
        }else {
            State state = new State(j,buyPrice);
            if (cache.containsKey(state)) {
                return cache.get(state);
            }
            //System.out.println("here");
            ArrayList<Integer> options = new ArrayList<>();
            if (prices[j] > buyPrice) {
                options.add(max(prices,j+1,accumulatedProfit+(prices[j]-buyPrice),Integer.MAX_VALUE));
            }

            if (buyPrice == Integer.MAX_VALUE) {
                //Nothing bought yet
                //then buy
                //System.out.println("here1");
                options.add(max(prices,j+1,accumulatedProfit,prices[j]));
            }


            //skip this option
            options.add(max(prices,j+1,accumulatedProfit,buyPrice));
            //System.out.println(options);
            int maxVal = Collections.max(options);
            cache.put(state,maxVal);
            return Collections.max(options);
        }
    }
}
class Solution {
    public int maxProfit(int[] prices) {
//        DP dp = new DP();
//        return dp.max(prices,0,0,Integer.MAX_VALUE);
        ArrayList<Integer> dp = new ArrayList<>();
        for (int j = 0;j<dp.size();++j) {
            dp.add(0);
        }

        //dp.set(0,0);

        for (int j = 1;j<prices.length;++j) {
            ArrayList<Integer> options = new ArrayList<>();
            options.add(0);
            for (int k = j-1;k>=0;--k) {
                if (prices[k] <= prices[j]) {
                    options.add(prices[j]-prices[k] + dp.get(k));
                }
            }

            dp.set(j,Collections.max(options));
        }

        return Collections.max(dp);


    }
}
