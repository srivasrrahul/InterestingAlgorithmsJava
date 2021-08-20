package leaderboard;

import java.util.*;

class Leaderboard {

    HashMap<Integer,Integer> leaders = new HashMap<>();
    TreeMap<Integer, HashSet<Integer>> scores = new TreeMap<>();
    public Leaderboard() {

    }

    public void addScore(int playerId, int score) {
        leaders.put(playerId,score);
        HashSet<Integer> players = scores.getOrDefault(score,new HashSet<>());
        players.add(playerId);
    }

    public int top(int K) {
        NavigableMap<Integer,HashSet<Integer>> reverseMap = scores.descendingMap();
        int count = 0;
        int sum = 0;
        for (Map.Entry<Integer,HashSet<Integer>> entry : reverseMap.entrySet()) {
            if (count < K) {
                for (Integer playerId : entry.getValue()) {
                    ++count;
                    sum += entry.getKey();
                }
            }else {
                break;
            }
        }

        return sum;
    }

    public void reset(int playerId) {
        Integer score = leaders.get(playerId);
        HashSet<Integer> players = scores.get(score);
        players.remove(playerId);
        if (players.size() == 0) {
            scores.remove(score);
        }

        leaders.remove(playerId);
    }
}
