package lc2;

import java.util.HashMap;
import java.util.Map;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null)
            return false;

        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        map.put(root, sum);
        while (map.size() > 0) {
            Map<TreeNode, Integer> temp = new HashMap<TreeNode, Integer>();
            for (TreeNode node : map.keySet()) {
                if (node.left == null && node.right == null && map.get(node) == node.val)
                    return true;

                if (node.left != null)
                    temp.put(node.left, map.get(node) - node.val);
                if (node.right != null)
                    temp.put(node.right, map.get(node) - node.val);
            }
            map = temp;
        }
        return false;
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && sum == root.val)
            return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}