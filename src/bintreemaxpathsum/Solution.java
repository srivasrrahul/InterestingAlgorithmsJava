package bintreemaxpathsum;



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

class Pair {
    private int nodeStartedPathSum;
    private int anyPathSum;

    public Pair(int nodeStartedPathSum, int anyPathSum) {
        this.nodeStartedPathSum = nodeStartedPathSum;
        this.anyPathSum = anyPathSum;
    }

    public int getNodeStartedPathSum() {
        return nodeStartedPathSum;
    }

    public int getAnyPathSum() {
        return anyPathSum;
    }
}
class Solution {
    Pair itr(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new Pair(node.val,node.val);
        }

        Pair leftAnswer = null;
        if (node.left == null) {
            leftAnswer = itr(node.left);
        }

        Pair rightAnswer = null;
        if (node.right == null) {
            rightAnswer = itr(node.right);
        }

        if (node.left == null) {
            int currentStartedPathSum = node.val + rightAnswer.getNodeStartedPathSum();
            return new Pair(currentStartedPathSum,
                            Math.max(node.val,Math.max(currentStartedPathSum,rightAnswer.getAnyPathSum())));
        }

        if (node.right == null) {
            int currentStartedPathSum = node.val + leftAnswer.getNodeStartedPathSum();
            return new Pair(currentStartedPathSum,
                    Math.max(node.val,Math.max(currentStartedPathSum,leftAnswer.getAnyPathSum())));
        }

        int opt1 = node.val + leftAnswer.getNodeStartedPathSum();
        int opt2 = node.val + rightAnswer.getNodeStartedPathSum();
        int opt3 = node.val + leftAnswer.getNodeStartedPathSum() + rightAnswer.getNodeStartedPathSum();

        return new Pair(Math.max(opt1,opt2),Math.max(opt3,Math.max(leftAnswer.getAnyPathSum(),rightAnswer.getAnyPathSum())));
    }
    public int maxPathSum(TreeNode root) {
        Pair pair = itr(root);
        return Math.max(pair.getNodeStartedPathSum(),pair.getNodeStartedPathSum());
    }
}
