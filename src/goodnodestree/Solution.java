package goodnodestree;



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Counter {
    private int m_Counter = 0;

    public int getCounter() {
        return m_Counter;
    }

    void count(TreeNode current, int maxValue) {
        if (current == null) {
            return;
        }

        maxValue = Math.max(maxValue,current.val);
        if (current.val > maxValue) {
            ++m_Counter;
        }

        count(current.left,maxValue);
        count(current.right,maxValue);
    }
}
class Solution {

    public int goodNodes(TreeNode root) {
        Counter counter = new Counter();
        counter.count(root,root.val);
        return counter.getCounter();
    }
}
