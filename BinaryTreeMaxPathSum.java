package lc;

public class BinaryTreeMaxPathSum {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(-2);
		root.right = new TreeNode(-3);
		root.right.left = new TreeNode(-2);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.left.left.left = new TreeNode(-1);
		System.out.println(new BinaryTreeMaxPathSum().maxPathSum(root));
	}

	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcodebinary-tree-maximum-path-sum.html
	 */
	public int maxPathSum(TreeNode root) {
		int[] res = maxPathSums(root);
		return res[1];
	}

	public int[] maxPathSums(TreeNode root) {
		int[] res = new int[2];
		if (root == null) {
			res[0] = res[1] = Integer.MIN_VALUE;
			return res;
		}
		int[] fromLeft = maxPathSums(root.left), fromRight = maxPathSums(root.right);

		/*
		 * temp1 only considers left child and the current node
		 * temp2 only considers right child and the current node
		 */
		int temp1 = fromLeft[0] > 0 ? (fromLeft[0] + root.val) : root.val;
		int temp2 = fromRight[0] > 0 ? (fromRight[0] + root.val) : root.val;

		/*
		 * res[0]: the maximum sum that includes the current node (root)
		 * res[1]: the overall maximum sum, which may be from:
		 * 1. The maximum sum from the left child (fromLeft[1])
		 * 2. The maximum sum from the right child (fromRight[1])
		 * 3. The maximum sum of the current node, the left and right children 
		 * 4. The larger value between left child and right child, plus the current node
		 */
		res[0] = Math.max(temp1, temp2);
		res[1] = Math.max(Math.max(fromLeft[1], fromRight[1]), Math.max(temp1 + temp2 - root.val, res[0]));

		return res;
	}
}
