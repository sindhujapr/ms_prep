package lc2;

import java.util.HashMap;
import java.util.Map;

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

        return Math.min(minDepth(node.left, depth), minDepth(node.right, depth));
    }

    public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        else {
            int leftDepth = root.left != null ? minDepth(root.left) : Integer.MAX_VALUE;
            int rightDepth = root.right != null ? minDepth(root.right) : Integer.MAX_VALUE;
            return Math.min(leftDepth, rightDepth) + 1;
        }
    }

    /*
     * non-recursion
     */
    public int minDepth3(TreeNode root) {
        if(root == null)
            return 0;

        int min = Integer.MAX_VALUE;
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        map.put(root, 1);
        while(map.size() > 0) {
            Map<TreeNode, Integer> temp = new HashMap<TreeNode, Integer>();
            for(TreeNode node : map.keySet()) {
                if(node.left == null && node.right == null)
                    return Math.min(min, map.get(node));
                else {
                    if(node.left != null)
                       temp.put(node.left, map.get(node)+1);
                    
                    if(node.right != null)
                        temp.put(node.right, map.get(node)+1);
                }
            }
            map = temp;
        }
        return min;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(new MinimumDepthOfBinaryTree().minDepth3(root));
    }
}