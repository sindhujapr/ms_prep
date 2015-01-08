package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
    if (root == null)
        return 0;

    return minDepth(root, 0);
    }

    private int minDepth(TreeNode node, int depth) {
    if (node == null)
        return Integer.MAX_VALUE;

    depth++;

    /*
     * this is leaf node. for non-leaf node, we should return the depth of
     * the the non-null child, either left or right.
     */
    if (node.left == null && node.right == null)
        return depth;

    return Math
        .min(minDepth(node.left, depth), minDepth(node.right, depth));
    }

    public int minDepth2(TreeNode root) {
    if (root == null)
        return 0;
    if (root.left == null && root.right == null)
        return 1;
    else {
        int leftDepth = root.left != null ? minDepth(root.left)
            : Integer.MAX_VALUE;
        int rightDepth = root.right != null ? minDepth(root.right)
            : Integer.MAX_VALUE;
        return Math.min(leftDepth, rightDepth) + 1;
    }
    }
    
    /*
     * non-recursion
     */
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;

        /*
         * we have to associate a node with its depth. however,
         * we cannot use map because the elements in map are not 
         * in order. that means, we have no way to retrieve the 
         * last-added node. Thus here we use two lists to maintain
         * these two piece of information.
         */
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        List<Integer> depths = new ArrayList<Integer>();
        nodes.add(root);
        depths.add(1);

        int min = Integer.MAX_VALUE;
        while(!nodes.isEmpty()) {
            TreeNode node = nodes.remove(nodes.size()-1);
            int depth = depths.remove(depths.size()-1);
            
            if(node.left == null && node.right == null && depth < min)
                min = depth;
            
            if(node.left != null) {
                nodes.add(node.left);
                depths.add(depth+1);
            }
             
            if(node.right != null) {
                nodes.add(node.right);
                depths.add(depth+1);
            }
        }
        
        return min;
    }
}