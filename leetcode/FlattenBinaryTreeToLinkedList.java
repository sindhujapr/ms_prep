package lc2;

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

    public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        while(list.size() > 0) {
            TreeNode node = list.remove(list.size()-1);
            if(node.left == null && node.right == null) {
                continue;
            } else if(node.left == null && node.right != null) {
                list.add(node.right);
            } else if(node.left != null && node.right == null) {
                node.right = node.left;
                node.left = null;
                list.add(node.right);
            } else {
                TreeNode temp = node.right;
                
                TreeNode leftMost = node.left;
                while(leftMost.right != null)
                    leftMost = leftMost.right;
                
                leftMost.right = temp;
                node.right = node.left;
                node.left = null;
                
                list.add(temp);
                list.add(node.right);
            }
        }
    }

    /*
     * time limit exceeded since we use a while loop to find the right most node
     * Not sure whether any other problems
     */
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode rightMost = null;
        if(root.left != null) {
            flatten(root.left);
            rightMost = root.left;
            while(rightMost.right != null)
                rightMost = rightMost.right;
        }

        
        flatten(root.right);
        
        if(root.left != null) {
            TreeNode right = root.right;
            root.right = root.left;
            rightMost.right = right;
        }
    }
    
    public void flatten2(TreeNode root) {
        if (root == null)
            return;

        List<TreeNode> stack = new ArrayList<TreeNode>();
        stack.add(root);
        TreeNode tail = null;

        while (!stack.isEmpty()) {
            TreeNode node = stack.remove(stack.size() - 1);

            if (node.right != null)
                stack.add(node.right);

            if (node.left != null)
                stack.add(node.left);

            if (tail != null) {
                tail.right = node;
                tail.left = null;
            }
            tail = node;
        }
    }
}
