package parallelcourses;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Graph {
    private int n;
    private HashMap<Integer, HashSet<Integer>> nodes = new HashMap<>();

    public Graph(int n) {
        this.n = n;
        for (int j = 0;j<n;++j) {
            nodes.put(j,new HashSet<>());
        }
    }

    void add(int u,int v) {
        nodes.get(u).add(v);
    }

    HashSet<Integer> getSources() {
        HashSet<Integer> sources = new HashSet<>(nodes.keySet());
        for (Integer u : nodes.keySet()) {
            for (Integer v : nodes.get(u)) {
                sources.remove(v);
            }
        }

        return sources;
    }

    void remove(HashSet<Integer> lst) {
        nodes.keySet().removeAll(lst);
    }

    Set<Integer> getNodes() {
        return nodes.keySet();
    }
}
class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        Graph graph = new Graph(n);
        for (int []edge : relations) {
            graph.add(edge[0]-1,edge[1]-1);
        }

        HashSet<Integer> sources = graph.getSources();
        int semester = 0;

        while (sources.isEmpty() == false) {
            ++semester;
            graph.remove(sources);
            sources = graph.getSources();
        }

        if (graph.getNodes().isEmpty()) {
            return semester;
        }else {
            return -1;
        }


    }
}