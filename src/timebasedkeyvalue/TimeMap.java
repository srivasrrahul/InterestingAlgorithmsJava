package timebasedkeyvalue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeMap {

    private HashMap<String, TreeMap<Integer,String>> m_TimeMap = new HashMap<>();
    /** Initialize your data structure here. */
    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> sortedMap = m_TimeMap.getOrDefault(key,new TreeMap<>());
        sortedMap.put(timestamp,value);

        m_TimeMap.put(key,sortedMap);
    }

    public String get(String key, int timestamp) {
        if (m_TimeMap.containsKey(key) == false) {
            return "";
        }

        TreeMap<Integer,String> sortedMap = m_TimeMap.get(key);

        //System.out.println(sortedMap);
        Map.Entry<Integer,String> ceiling = sortedMap.floorEntry(timestamp);
        if (ceiling == null) {
            return "";
        }
        return ceiling.getValue();

    }
}
