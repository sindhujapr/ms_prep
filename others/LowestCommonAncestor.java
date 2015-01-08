package others;

/*
 * This is for binary tree, not binary search tree, whose solution is LCAOfBST.java.
 * 
 * http://www.fusu.us/2013/06/p2-lowest-common-ancestor-in-binary-tree.html
 * http://www.topcoder.com/tc?d1=tutorials&d2=lowestCommonAncestor&module=Static
 * 
 * http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
 * http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-ii.html
 */
public class LowestCommonAncestor {
    public static TreeNode lca(TreeNode root, TreeNode node1, TreeNode node2) {
        if(root == null)
            return null;
        
        /*
         * at least one node matches, no need to continue and this is the LCA.
         * But here we suppose the other node exists in the tree.
         */
        if(root.val == node1.val || root.val == node2.val)
            return root;
        
        TreeNode left = lca(root.left, node1, node2);
        TreeNode right = lca(root.right, node1, node2);
        
        // the left and right children each contains one node
        if(left != null && right != null)
            return root;
        
        return left != null ? left : right;
    }
}