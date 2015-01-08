package lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
    public String toString() { return Integer.toString(val); }
}

public class BinaryTreeInorderTraversal {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
            return result;

        ArrayList<TreeNode> stack = new ArrayList<TreeNode>();
        Set<TreeNode> set = new HashSet<TreeNode>();
        stack.add(root);
        
        while(!stack.isEmpty()) {
            TreeNode node = stack.remove(stack.size()-1);
            if(set.contains(node)) {
                result.add(node.val);
            } else {
                if(node.right != null)
                    stack.add(node.right);
                stack.add(node);
                set.add(node);
                if(node.left != null)
                   stack.add(node.left);
            }
        }

        return result;
    }
}
