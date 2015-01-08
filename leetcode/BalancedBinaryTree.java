package lc2;

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

        int leftHeight = height(root.left);
        if (leftHeight == -1)
            return -1;

        int rightHeight = height(root.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    public boolean isBalanced2(TreeNode root) {
        return height(root, 1) != -1;
    }
    
    private int height(TreeNode node, int h) {
        if(node == null)
            return h-1;

        if(node.left == null && node.right == null)
            return h;

        int lh = height(node.left, h+1);
        int rh = height(node.right, h+1);
        
        if(lh == -1 || rh == -1 || Math.abs(lh-rh) > 1)
            return -1;
        
        return Math.max(lh, rh);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        System.out.println(new BalancedBinaryTree().isBalanced(root));
    }
}