package canfinishcourses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Graph {
    private int n;
    private HashMap<Integer,HashSet<Integer>> nodes = new HashMap<>();

    public Graph(int n) {
        this.n = n;
        for (int j = 0;j<n;++j) {
            nodes.put(j,new HashSet<>());
        }
    }

    HashSet<Integer> getNeigbours(int u) {
        return nodes.get(u);
    }

    void addEdge(int u,int v) {
        nodes.get(u).add(v);
    }

    HashSet<Integer> getSources() {
        HashSet<Integer> sources = new HashSet<>();
        for (int x : nodes.keySet()) {
            sources.add(x);
        }

        for (int u : nodes.keySet()) {
            if (nodes.get(u).size() > 0) {
                for (int v : nodes.get(u)) {
                    sources.remove(v);
                }
            }
        }

        return sources;
    }

    void remove(HashSet<Integer> sources) {
        for (Integer s : sources) {
            nodes.remove(s);
        }
    }

    int countNodes()  {
        return nodes.size();
    }
}
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for (int [] edge : prerequisites) {
            graph.addEdge(edge[0],edge[1]);
        }

        while (graph.countNodes() != 0) {
            HashSet<Integer> sources = graph.getSources();
            if (sources.isEmpty()) {
                return false;
            }

            graph.remove(sources);
        }

        return true;
    }
}
