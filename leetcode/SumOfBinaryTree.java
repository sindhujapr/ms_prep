package leetcode;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
    val = x;
    }
}

public class SumOfBinaryTree {
    public int sumNumbers(TreeNode root) {
    return sum(root, 0);
    }

    private int sum(TreeNode node, int value) {
    if (node == null)
        return 0;

    int total = 10 * value + node.val;
    if (node.left == null && node.right == null)
        return total;
    else
        return sum(node.left, total) + sum(node.right, total);
    }
}