package clonegraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class DFSVisit {
    HashSet<Integer> visited = new HashSet<>();
    Node itr(Node node) {
       visited.add(node.val);
       Node newNode = new Node(node.val);
       for (Node neighbour : node.neighbors) {
           if (visited.contains(neighbour) == false) {
               Node neigbourNewNode = itr(neighbour);
               newNode.neighbors.add(neigbourNewNode);
           }
       }

       return newNode;
    }
}
class Solution {
    public Node cloneGraph(Node node) {
        DFSVisit dfsVisit = new DFSVisit();
        return dfsVisit.itr(node);

    }
}