package test;

import java.util.*;

/**
 * Created by qingcunz on 11/7/14.
 */
public class BinaryTreeVerticalOrder {
    private static int leftDepth = Integer.MAX_VALUE;
    private static int rightDepth = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = init();
//        depth(root, 0);

//        for(int i = leftDepth; i <= rightDepth; i++)
//            verticalOrder(root, i, 0);
        verticalOrder(root);
    }

    public static void verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        traversal(root, map, 0);

        for(Integer dist: map.keySet())
            System.out.println(dist + ":\t" + map.get(dist));
    }

    private static void traversal(TreeNode root, Map<Integer, List<Integer>> map, int dist) {
        if(root == null)
            return;

        if(!map.containsKey(dist))
            map.put(dist, new ArrayList<Integer>());
        map.get(dist).add(root.val);

        traversal(root.left, map, dist-1);
        traversal(root.right, map, dist+1);
    }

    public static void verticalOrder(TreeNode root, int line, int h) {
        if(root == null)
            return;

        if(line == h)
            System.out.println(root.val);

        verticalOrder(root.left, line, h-1);
        verticalOrder(root.right, line, h+1);
    }

    public static void depth(TreeNode root, int depth) {
        if(root == null)
            return;

        leftDepth = Math.min(leftDepth, depth);
        rightDepth = Math.max(rightDepth, depth);

        depth(root.left, depth-1);
        depth(root.right, depth+1);
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);
        return root;
    }

    private static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
