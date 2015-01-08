package lc2;

import java.util.ArrayList;
import java.util.List;

public class MaxDepthOfBinaryTree {
    public int maxDepth2(TreeNode root) {
        return maxDepth(root, 0);
    }
    
    private int maxDepth(TreeNode root, int h) {
        if(root == null)
            return h;
        
        int left = maxDepth(root.left, h+1);
        int right = maxDepth(root.right, h+1);
        return Math.max(left, right);
    }
    
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        
        int depth = 0;
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        while(list.size() > 0) {
            depth++;
            List<TreeNode> temp = new ArrayList<TreeNode>();
            for(TreeNode node : list) {
                if(node.left != null)
                    temp.add(node.left);
                if(node.right != null)
                    temp.add(node.right);
            }
            list = temp;
        }
        return depth;
    }
}