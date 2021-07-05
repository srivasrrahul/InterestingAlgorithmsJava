package allpathsourcetarget;

import java.util.*;
import java.util.stream.Collectors;

class Graph {
    private ArrayList<HashSet<Integer>> m_Edges = new ArrayList<>();

    public LinkedList<List<Integer>> getPaths() {
        return m_Paths;
    }

    private LinkedList<List<Integer>> m_Paths = new LinkedList<>();

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


    void findPath(int u,List<Integer> pathTillNow) {
        if (u == m_Edges.size()-1) {
            pathTillNow.add(u);
            m_Paths.add(pathTillNow);
        }else {
            for (Integer v : m_Edges.get(u)) {
                List<Integer> lst = pathTillNow.stream().collect(Collectors.toList());
                lst.add(u);
                findPath(v,lst);
            }
        }
    }
}

public class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] g) {
        Graph graph = new Graph(g.length);
        for (int j = 0;j<g.length;++j) {
            int [] edge = g[j];
            for (Integer e : edge) {
                graph.addEdge(j,e);
            }
        }

        graph.findPath(0,new LinkedList<>());

        return graph.getPaths();
    }
}
