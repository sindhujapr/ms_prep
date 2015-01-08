package leetcode;

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    /*
     * use different values to denote different status: 1) -1 means the tree is
     * not balanced 2) otherwise means the tree's height
     */
    private int height(TreeNode root) {
        if (root == null)
            return 0;

        int lh = height(root.left), rh = height(root.right);
        if (lh == -1 || rh == -1 || Math.abs(lh-rh) > 1)
            return -1;

        return 1 + Math.max(lh, rh);
    }
}