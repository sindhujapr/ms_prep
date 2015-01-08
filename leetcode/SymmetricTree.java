package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
    if (root == null)
        return true;

    List<TreeNode> nodes = new ArrayList<TreeNode>();
    nodes.add(root);
    return isSymmetric(nodes);
    }

    private boolean isSymmetric(List<TreeNode> nodes) {
    boolean flag = true;
    for (TreeNode node : nodes)
        if (node != null)
        flag = false;
    if (flag)
        return true;

    int size = nodes.size();
    for (int i = 0; i < size / 2; i++) {
        int i1 = size - i - 1;
        TreeNode node1 = nodes.get(i);
        TreeNode node2 = nodes.get(i1);

        if (node1 == null && node2 != null)
        return false;
        if (node1 != null && node2 == null)
        return false;
        if (node1 != null && node2 != null && node1.val != node2.val)
        return false;
    }

    List<TreeNode> childNodes = new ArrayList<TreeNode>();
    for (TreeNode node : nodes) {
        if (node != null) {
        childNodes.add(node.left);
        childNodes.add(node.right);
        } else {
        childNodes.add(null);
        childNodes.add(null);
        }
    }

    return isSymmetric(childNodes);
    }

    public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);

    root.left.right = new TreeNode(3);
    root.right.left = new TreeNode(3);

    SymmetricTree instance = new SymmetricTree();
    System.out.println(instance.isSymmetric(root));
    }
}