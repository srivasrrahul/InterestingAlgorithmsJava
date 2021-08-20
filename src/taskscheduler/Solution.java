 package taskscheduler;

 import scala.Int;

import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character,Integer> taskCount = new HashMap<>();


        for (Character ch : tasks) {
            int existingCount = taskCount.getOrDefault(ch,0);
            taskCount.put(ch,existingCount+1);
        }

        TreeMap<Integer, HashSet<Character>> maxAmountTask = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Character,Integer> entry : taskCount.entrySet()) {
            HashSet<Character> set = maxAmountTask.getOrDefault(entry.getValue(),new HashSet<>());
            set.add(entry.getKey());
            maxAmountTask.put(entry.getValue(),set);
        }

        int time = 1;
        int executed = 0;
        HashMap<Character, Integer> lastExecutedCount = new HashMap<>();
        TreeMap<Integer,Character> barredSet = new TreeMap<>();
        while (executed < tasks.length) {
            char chExecuted = '#';

            barredSet.headMap(time-n).clear();
            for (Map.Entry<Integer, HashSet<Character>> entry : maxAmountTask.entrySet()) {

                for (Character ch : entry.getValue()) {
                    if (lastExecutedCount.containsKey(ch)) {
                        if (time-lastExecutedCount.get(ch) > n) {
                            lastExecutedCount.put(ch,time);
                            ++executed;
                            chExecuted = ch;
                            break;
                        }
                    }else {
                        ++executed;
                        lastExecutedCount.put(ch,time);
                        chExecuted = ch;
                        break;
                    }
                }

                if (chExecuted != '#') {
                    break;
                }
            }

            if (chExecuted != '#') {
                int currCount = taskCount.get(chExecuted);
                HashSet<Character> set = maxAmountTask.get(currCount);
                set.remove(chExecuted);

                if (currCount == 1) {
                    taskCount.remove(chExecuted);
                }else {
                    taskCount.put(chExecuted,currCount-1);
                    HashSet<Character> set1 = maxAmountTask.getOrDefault(currCount-1,new HashSet<>());
                    set1.add(chExecuted);
                    maxAmountTask.put(currCount-1,set1);
                }

                barredSet.put(time,chExecuted);
            }

            if (executed < tasks.length) {
                ++time;
            }

        }

        return time;
    }
}
