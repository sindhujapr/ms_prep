package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FlattenBinaryTreeToLinkedList {
    public void flatten1(TreeNode root) {
    flattenAndReturn(root);
    }

    private TreeNode flattenAndReturn(TreeNode root) {
    if (root == null)
        return null;

    TreeNode leftTail = flattenAndReturn(root.left);
    if (leftTail == null) {
        leftTail = root;
    }

    TreeNode rightTail = flattenAndReturn(root.right);
    if (rightTail == null) {
        rightTail = leftTail;
    }

    TreeNode temp = root.right;
    root.right = root.left;
    leftTail.right = temp;
    root.left = null;
    return rightTail;
    }
    
    public void flatten2(TreeNode root) {
        if(root == null)
            return;

        List<TreeNode> stack = new ArrayList<TreeNode>();
        stack.add(root);
        TreeNode tail = null;

        while(!stack.isEmpty()) {
            TreeNode node = stack.remove(stack.size()-1);

            if(node.right != null)
                stack.add(node.right);
                
            if(node.left != null)
                stack.add(node.left);
            
            if(tail != null) {
                tail.right = node;
                tail.left = null;
            }
            tail = node;
        }
    }
}