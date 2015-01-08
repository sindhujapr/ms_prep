package lc;

public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        /*
         * if we don't use long, a tree with single node Integer.MAX_VALUE will be
         * considered invalid
         */
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean valid(TreeNode root, long lower, long upper) {
        if(root == null)
            return true;
        
        if(root.val <= lower || root.val >= upper)
            return false;
        
        return valid(root.left, lower, root.val) && valid(root.right, root.val, upper);
    }
}
