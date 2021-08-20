package combinationsum;

import java.util.*;
import java.util.stream.Collectors;

class Target {
    private int [] candidates;

    private HashMap<Integer,Set<List<Integer>>> cache = new HashMap<>();


    public Target(int[] candidates) {
        this.candidates = candidates;

        Arrays.sort(this.candidates);
    }
    public Set<List<Integer>> combinationSum(int pending) {
        if (pending == 0) {
            return new HashSet<>();
        }

        if (pending < 0) {
            return null;
        }

        if (cache.containsKey(pending)) {
            return cache.get(pending);
        }
        HashSet<List<Integer>> retValue = new HashSet<>();
        for (int candidate : candidates) {
            if (pending-candidate > 0) {
                Set<List<Integer>> pendingValue = combinationSum(pending-candidate);
                if (pendingValue != null) {
                    for (List<Integer> lst : pendingValue) {
                        List<Integer> newLst = lst.stream().collect(Collectors.toList());
                        newLst.add(candidate);
                        Collections.sort(newLst);
                        retValue.add(newLst);
                    }
                }
            }else {
                if (pending == candidate) {
                    List<Integer> newLst = new LinkedList<>();
                    newLst.add(candidate);
                    retValue.add(newLst);
                }
            }
        }

        cache.put(pending,retValue);
        return retValue;

    }

}
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Target targets = new Target(candidates);
        Set<List<Integer>> lsts = targets.combinationSum(target);
        List<List<Integer>> retValue = new ArrayList<>();
        for (List<Integer> lst :lsts) {
            retValue.add(lst);
        }

        return retValue;

    }
}