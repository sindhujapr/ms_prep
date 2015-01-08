package test;

import java.util.*;

// Find all paths that sum to a given value. The path may start and end at any node
public class FindPathInTree {
    public static void main(String[] args) {
        TreeNode root = init();

//        Set<TreeNode> visited = new HashSet<TreeNode>();
//        findPath(root, 3, 3, new ArrayList<Integer>(), visited);

        List<List<Integer>> result = pathSum(root, 3);
        for(List<Integer> list : result)
            System.out.println(list);
    }

    // My code
    public static void findPath(TreeNode root, int sum, int target, List<Integer> list, Set<TreeNode> visited) {
        if (root == null)
            return;

        list.add(root.val);
        sum -= root.val;

        //output the path that ends with current node
        if (sum == 0)
            System.out.println("Path found: " + list);


        findPath(root.left, sum, target, list, visited);
        findPath(root.right, sum, target, list, visited);
        list.remove(list.size() - 1);

        /*
         * The reason we need to use set visited:
         * Every time we visit a node, we also start from its child node. That means, eg, for
         * the 3rd level nodes (root is 1st level), we will evaluate the path starting from
         * them twice, once for paths start from 2nd level, the other for paths start from
         * 3rd level
         */
        if(!visited.contains(root)) {
            // start from the children node
            findPath(root.left, target, target, new ArrayList<Integer>(), visited);
            findPath(root.right, target, target, new ArrayList<Integer>(), visited);
        }

        visited.add(root);
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(3);
        root.right.left.left = new TreeNode(3);
        return root;
    }

    private static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void sum(TreeNode root, int target, List<Integer> branch, List<List<Integer>> result) {
        if(root == null)
            return;

        branch.add(root.val);

        target -= root.val;
        if(target == 0) {
            result.add(new ArrayList<Integer>(branch));
            return;
        }

        sum(root.left, target, branch, result);
        sum(root.right, target, branch, result);
        branch.remove(branch.size() - 1);
    }

    // Guibin's code
    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        List<TreeNode> q = new ArrayList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove(q.size()-1);
            List<Integer> path = new ArrayList<Integer>();

            sum(curr, target, path, result);

            if (curr.left != null)
                q.add(curr.left);
            if (curr.right != null)
                q.add(curr.right);
        }

        return result;
    }
}
