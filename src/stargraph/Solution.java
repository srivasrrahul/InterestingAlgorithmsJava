package stargraph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Graph {
    private Map<Integer, Integer> m_Graph = new HashMap<>();
    void addEdge(int u,int v) {
        m_Graph.putIfAbsent(u,0);
        int current = m_Graph.get(u);
        m_Graph.put(u,current+1);
    }

    int checkStar() {
        int s = m_Graph.size();
        for (Map.Entry<Integer,Integer> entry : m_Graph.entrySet()) {
            if (entry.getValue() == s-1) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
public class Solution {
    public int findCenter(int[][] edges) {
        Graph graph = new Graph();
        for (int j = 0;j<edges.length;++j) {
            graph.addEdge(edges[j][0],edges[j][1]);
            graph.addEdge(edges[j][1],edges[j][0]);
        }
        return graph.checkStar();
    }


}

