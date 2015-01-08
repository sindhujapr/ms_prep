package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qingcunz on 12/8/14.
 */
public class FlatBinaryTreeToRight {
    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
        @Override public String toString() { return Integer.toString(val); }
    }

    public static void main(String[] args) {
        TreeNode root = flat(init());
        while(root != null) {
            System.out.print(root.val + "\t" + root.left + "\t");
            root = root.right;
        }

        System.out.println();
        root = flat1(init());
        while(root != null) {
            System.out.print(root.val + "\t" + root.left + "\t");
            root = root.right;
        }
    }

    private static TreeNode prev = null;
    public static TreeNode flat1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(root == null)
            return null;

        Set<TreeNode> visited = new HashSet<TreeNode>();
        TreeNode newHead = null;
        list.add(root);
        while(!list.isEmpty()) {
            TreeNode node = list.remove(list.size()-1);

            if(visited.contains(node)) {
                if(prev == null) {
                    newHead = node;
                } else {
                    prev.right = node;
                    node.left = null;
                }

                prev = node;
                continue;
            }

            if(node.right != null)
                list.add(node.right);

            list.add(node);
            visited.add(node);

            if(node.left != null)
                list.add(node.left);
        }

        return newHead;
    }

    public static TreeNode flat(TreeNode root) {
        if(root == null)
            return null;

        TreeNode leftMost = root;
        while(leftMost.left != null)
            leftMost = leftMost.left;

        flatAndReturnTail(root);
        return leftMost;
    }

    private static TreeNode flatAndReturnTail(TreeNode root) {
        if(root == null)
            return null;

        TreeNode leftTail = flatAndReturnTail(root.left);

        if(leftTail != null) {
            leftTail.right = root;
            root.left = null;
        }

        if(root.right != null) {
            TreeNode rightHead = root.right;
            while (rightHead != null && rightHead.left != null)
                rightHead = rightHead.left;

            TreeNode temp = root.right;
            root.right = rightHead;
            root.left = null;

            TreeNode rightTail = flatAndReturnTail(temp);
            return rightTail;
        } else {
            return root;
        }
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);

        root.right.left.left = new TreeNode(9);
        root.right.right.left = new TreeNode(10);
        return root;
    }
}
