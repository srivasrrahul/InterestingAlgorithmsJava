package courseschedule2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Graph {
    private int n;
    private HashMap<Integer,HashSet<Integer>> nodes = new HashMap<>();

    public Graph(int n) {
        this.n = n;
        for (int j = 0;j<n;++j) {
            nodes.put(j,new HashSet<>());
        }
    }

    void addEdge(int u,int v) {
        nodes.get(u).add(v);
    }

    HashSet<Integer> getNeigbours(int u) {
        return nodes.get(u);
    }

    HashSet<Integer> getSources() {
        HashSet<Integer> sources = new HashSet<>();
        for (Integer u : nodes.keySet()) {
            sources.add(u);
        }

        for (Integer u : nodes.keySet()) {
            for (Integer v : nodes.get(u)) {
                sources.remove(v);
            }
        }

        return sources;
    }

    void removeNodes(HashSet<Integer> lst) {
        for (Integer u : lst) {
            nodes.remove(u);
        }

    }

    Set<Integer> getCurrentNodes() {
        return nodes.keySet();
    }


}
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for (int [] edge : prerequisites) {
            graph.addEdge(edge[0],edge[1]);
        }

        HashSet<Integer> sources = graph.getSources();
        ArrayList<Integer> ordering = new ArrayList<>();
        while (sources.isEmpty() == false) {
            ordering.addAll(sources);
            graph.removeNodes(sources);
            sources = graph.getSources();
        }

        if (graph.getCurrentNodes().size() == 0) {
            //possible
            return ordering.stream().mapToInt(x -> x).toArray();
        }else {
            return new int[0];
        }

    }

    public static void main(String[] args) {

    }
}
