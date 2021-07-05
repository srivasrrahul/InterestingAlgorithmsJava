package townjudge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//package townjudge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
        m_Edges.get(u-1).add(v-1);
    }

    List<Integer> getNoOutbound() {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int j = 0;j<m_Edges.size();++j) {

            if (m_Edges.get(j).size() == 0) {
                lst.add(j);
            }
        }

        return lst;
    }

    boolean checkEdge(int u) {

        for (int j = 0;j<m_Edges.size();++j) {
            HashSet<Integer> set = m_Edges.get(j);
            if (j != u) {
                if (set.contains(u) == false) {
                    //System.out.println(j + "," + set);
                    return false;
                }
            }
        }

        return true;
    }
}
class Solution {
    public int findJudge(int n, int[][] trust) {
        Graph graph = new Graph(n);

        for (int j = 0;j<trust.length;++j) {
            graph.addEdge(trust[j][0],trust[j][1]);
        }

        //System.out.println(graph);
        List<Integer> lst = graph.getNoOutbound();
        //System.out.println(lst);
        if (lst.size() == 0) {
            return -1;
        }

        for (Integer x : lst) {
            if (graph.checkEdge(x)) {
                return x+1;
            }
        }

        return -1;


    }
}