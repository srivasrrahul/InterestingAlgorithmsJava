package tweetscount;

import java.util.*;

class Filter {
    private int from;
    private int to;
    private String tweetName;

    public Filter(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getTweetName() {
        return tweetName;
    }

    public void setTweetName(String tweetName) {
        this.tweetName = tweetName;
    }

    Filter buildTweetName(String x) {
        Filter filter = new Filter(from,to);
        filter.setTweetName(x);
        return filter;
    }
}
class PolicyInterval {
    private final TreeMap<Integer, HashMap<String,Integer>> timeMapCount;
    private int interval;

    public PolicyInterval(TreeMap<Integer, HashMap<String, Integer>> timeMapCount, int interval) {
        this.timeMapCount = timeMapCount;
        this.interval = interval;
    }

    ArrayList<Integer> getCount(Filter filter) {
        HashMap<Integer,Integer> lst = new HashMap<>();
        SortedMap<Integer,HashMap<String,Integer>> sortedMap = timeMapCount.subMap(filter.getFrom()-1,filter.getTo()+1);
        for (Map.Entry<Integer,HashMap<String,Integer>> entry: sortedMap.entrySet()) {
            if (entry.getValue().containsKey(filter.getTweetName())) {
                int localInterval = (entry.getKey()-filter.getFrom())/this.interval;
                int existingCount = lst.getOrDefault(localInterval,0);
                lst.put(localInterval,existingCount+entry.getValue().get(filter.getTweetName()));
            }
        }

        return new ArrayList<>(lst.values());
    }


}
class TweetCounts {

    private TreeMap<Integer, HashMap<String,Integer>> timeMapCount = new TreeMap<>();
    private HashMap<String,Integer> timeInStringToMap = new HashMap<>();
    public TweetCounts() {
        timeInStringToMap.put("minute",60);
        timeInStringToMap.put("hour",60*60);
        timeInStringToMap.put("day",60*60*24);
    }

    public void recordTweet(String tweetName, int time) {
        HashMap<String,Integer> mapCount = timeMapCount.getOrDefault(time,new HashMap<>());
        int existingCount = mapCount.getOrDefault(tweetName,0);
        mapCount.put(tweetName,existingCount+1);
        timeMapCount.put(time,mapCount);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        PolicyInterval policyInterval = new PolicyInterval(timeMapCount,timeInStringToMap.get(freq));
        Filter filter = new Filter(startTime,endTime).buildTweetName(tweetName);
        return policyInterval.getCount(filter);
    }

    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("t1",1);
        tweetCounts.recordTweet("t1",2);
        tweetCounts.recordTweet("t1",60);
        tweetCounts.recordTweet("t1",3600);

        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour","t1",0,3601));
    }
}


