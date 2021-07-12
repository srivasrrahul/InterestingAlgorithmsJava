package lowestcommonancestor;




import java.util.*;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

class Solution {
     LinkedList<TreeNode> getPath(TreeNode current, TreeNode needle) {
        if (current == null) {
            return new LinkedList<>();
        }

        if (current.val == needle.val) {
            return new LinkedList<>(Arrays.asList(current));
        }

        LinkedList<TreeNode> left = getPath(current.left,needle);
        LinkedList<TreeNode> right = getPath(current.right,needle);

        if (left.size() == 0 && right.size() == 0) {
            return left;
        }

        if (left.size() == 0) {
            right.add(current);
            return right;
        }

        if (right.size() == 0) {
            left.add(current);
            return left;
        }

        return new LinkedList<>(); //not possible
     }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pPath = getPath(root,p);
        LinkedList<TreeNode> qPath = getPath(root,q);

        Iterator<TreeNode> pItr = pPath.descendingIterator();
        Iterator<TreeNode> qItr = qPath.descendingIterator();

        TreeNode lca = null;
        while (pItr.hasNext() && qItr.hasNext()) {
            TreeNode pCurr = pItr.next();
            TreeNode qCurr = qItr.next();
            if (pCurr.val == qCurr.val) {
                lca = pCurr;
            }else {
                return lca;
            }
        }

        //didn't find
        return lca;
    }
}