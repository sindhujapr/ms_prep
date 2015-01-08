package lc2;

import java.util.HashMap;
import java.util.Map;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        map.put(root, 0);
        
        int sum = 0;
        while(map.size() > 0) {
            Map<TreeNode, Integer> temp = new HashMap<TreeNode, Integer>();
            for(TreeNode node : map.keySet()) {
                int val = map.get(node) * 10 + node.val;
                if(node.left == null && node.right == null)
                    sum += val;
                
                if(node.left != null)
                    temp.put(node.left, val);
                if(node.right != null)
                    temp.put(node.right, val);
            }
            map = temp;
        }
        return sum;
    }

	public int sumNumbers1(TreeNode root) {
		return sum(root, 0);
	}

	private int sum(TreeNode node, int value) {
		if (node == null)
			return 0;

		int total = 10 * value + node.val;
		if (node.left == null && node.right == null)
			return total;

		return sum(node.left, total) + sum(node.right, total);
	}
}