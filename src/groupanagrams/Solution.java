package groupanagrams;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> sortedStrs = new HashMap<>();
        for (String s : strs) {
            char []arr = s.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            List<String> lst = sortedStrs.getOrDefault(sortedStr,new LinkedList<>());
            lst.add(sortedStr);
            sortedStrs.put(sortedStr,lst);
        }

        List<List<String>> retValue = new LinkedList<>();
        for (List<String> v : sortedStrs.values()) {
            retValue.add(v);
        }

        return retValue;

    }
}
