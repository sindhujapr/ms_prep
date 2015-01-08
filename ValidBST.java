package lc2;

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean valid(TreeNode node, int min, int max) {
        if(node == null)
            return true;
        if(node.val <= min || node.val >= max)
            return false;
        
        return valid(node.left, min, node.val) &&
        		valid(node.right, node.val, max);
    }
}