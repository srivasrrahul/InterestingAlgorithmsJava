package minverticesallnodes;

import java.util.*;
import java.util.stream.Collectors;

class Graph {
    private ArrayList<HashSet<Integer>> m_Edges = new ArrayList<>();


    @Override
    public String toString() {
        return "Graph{" +
                "m_Edges=" + m_Edges +
                '}';
    }

    public Graph(int n) {
        for (int j = 0;j<n;++j) {
            m_Edges.add(new HashSet<>());
        }
    }

    void addEdge(int u,int v) {
        m_Edges.get(u).add(v);
    }

    Set<Integer> getOutbound(int u) {
        return m_Edges.get(u);
    }

    List<Integer> getBaseNodes() {
        HashSet<Integer> nodeLst = new HashSet<>();
        for (int j = 0; j< m_Edges.size();++j) {
            nodeLst.add(j);
        }

        for (HashSet<Integer> edges : m_Edges) {
            edges.forEach(v -> {
                nodeLst.remove(v);
            });
        }

        return nodeLst.stream().collect(Collectors.toList());
    }


}
public class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Graph graph = new Graph(n);
        for (List<Integer> e : edges) {
            graph.addEdge(e.get(0),e.get(1));
        }

        return graph.getBaseNodes();
    }
}