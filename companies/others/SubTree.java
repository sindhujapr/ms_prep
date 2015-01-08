package test;

import java.util.*;

/**
 * Created by qingcunz on 12/10/14.
 */
public class SubTree {
    public static void main(String[] args) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        subtrees(init(), res);

        for(TreeNode node : res)
            System.out.println(node.val);
    }

    public static Set<Integer> subtrees(TreeNode root, List<TreeNode> res) {
        if(root == null)
            return Collections.emptySet();

        Set<Integer> left = subtrees(root.left, res);
        Set<Integer> right = subtrees(root.right, res);

        if(!left.contains(root.val) && !right.contains(root.val)) {
            Set<Integer> temp = new HashSet<Integer>(left);
            temp.removeAll(right);

            if(temp.size() == left.size())
                res.add(root);
        }

        Set<Integer> set = new HashSet<Integer>(left);
        set.addAll(right);
        set.add(root.val);
        return set;
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(2);
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
        @Override public String toString() { return Integer.toString(val); }
    }
}
