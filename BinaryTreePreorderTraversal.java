package lc;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
	/*
	 * iterative solution. Different from in-order traversal, we don't need the map or set
	 * to include the nodes that we have accessed and thus we can keep their values directly
	 * for next access.
	 */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;

        List<TreeNode> stack = new ArrayList<TreeNode>();
        stack.add(root);
        
        while(stack.size() > 0) {
            TreeNode node = stack.remove(stack.size()-1);
            res.add(node.val);
            
            if(node.right != null)
                stack.add(node.right);
            if(node.left != null)
                stack.add(node.left);
        }
        return res;
    }
}