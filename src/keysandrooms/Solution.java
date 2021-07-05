package keysandrooms;

import java.util.*;

class Graph {
    private ArrayList<List<Integer>> m_Edges = new ArrayList<>();

    public Graph(int n) {
        for (int j = 0;j<n;++j) {
            m_Edges.add(new ArrayList<Integer>());
        }
    }

    void addEdge(int u,int v) {
        m_Edges.get(u).add(v);
    }

    List<List<Integer>> getNeighbours() {
        return m_Edges;
    }

    Set<Integer> bfs(int s) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(s);

        HashSet<Integer> visited = new HashSet<>();

        while (q.isEmpty() == false) {
            int top = q.getFirst();
            q.poll();
            visited.add(top);
            List<Integer> neighbours = m_Edges.get(top);
            for (Integer neighbour : neighbours) {
                if (visited.contains(neighbour) == false) {
                    q.add(neighbour);
                }
            }
        }

        return visited;
    }

}
public class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Graph graph = new Graph(rooms.size());
        int u = 0;
        for (List<Integer> edges : rooms) {
            int finalU = u;
            edges.forEach(v -> {
                graph.addEdge(finalU,v);
            });
            ++u;
        }

        Set<Integer> visited = graph.bfs(0);
        return  (visited.size() == rooms.size()) ;


    }
}
