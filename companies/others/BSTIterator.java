package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qingcunz on 11/15/14.
 */
public class BSTIterator {
    // suppose all elements in A has at most 5 digits
    public static void countSort(int[] A) {
        int n = A.length;
        int[] count = new int[10], B = new int[n];

        for(int i = 0; i < 5; i++) {
            //sort based on digit i
            Arrays.fill(count, 0);
            int power = power(i);

            for(int j = 0; j < n; j++) {
                int index = A[j] / power % 10;
                count[index]++;
            }

            for(int j = 1; j < 10; j++)
                count[j] += count[j-1];

            for(int j = n-1; j >= 0; j--) {
                int bit = A[j] / power(i) % 10;
                B[--count[bit]] = A[j];
            }

            for(int j = 0; j < n; j++)
                A[j] = B[j];
        }

        System.out.println(Arrays.toString(A));
    }

    private static int power(int i) {
        int res = 1;
        while(i-- > 0)
            res *= 10;
        return res;
    }

    public static void countingSort(int[] a, int low, int high)
    {
        int[] counts = new int[high - low + 1]; // this will hold all possible values, from low to high
        for (int x : a)
            counts[x - low]++; // - low so the lowest possible value is always 0

        int current = 0;
        for (int i = 0; i < counts.length; i++)
        {
            Arrays.fill(a, current, current + counts[i], i + low); // fills counts[i] elements of value i + low in current
            current += counts[i]; // leap forward by counts[i] steps
        }
    }

    public static void main(String[] args) {
        int[] A = {3, 9999, 9, 12, 123, 5, 86, 15, 4233, 12334, 10, 22, 86, 100};
        countSort(A);

        BSTIterator instance = new BSTIterator(init());
//        for(int i = 0; i < 10; i++)
//            System.out.println(instance.next());

        System.out.println("search next");
        TreeNode root = init();
        for(int i = 1; i <= 7; i++)
            System.out.println(instance.searchNext(root, i));

        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(new TreeNode(1));
        list.add(new TreeNode(2));
        list.add(new TreeNode(3));
        System.out.println(list.toString());
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
        @Override public String toString() { return Integer.toString(val); }
    }

    List<TreeNode> stack = new ArrayList<TreeNode>();

    public BSTIterator(TreeNode root) {
        stack.add(root);
        while(root.left != null) {
            stack.add(root.left);
            root = root.left;
        }
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(2);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        return root;
    }

    /*
     * Another idea is to construct a threaded tree and destruct it
     * during the iteration. We need two special values (Integer.MIN_VALUE & Integer.MAX_VALUE)
     * to denote the start and end of the iteration.
     */
    public int next() {
        if(stack.size() == 0)
            return -1;

        TreeNode node = stack.remove(stack.size()-1);
        int res = node.val;
        if(node.right != null) {
            stack.add(node.right);
            node = node.right;

            while(node.left != null) {
                stack.add(node.left);
                node = node.left;
            }
        }

        return res;
    }

    // search in the tree the value that is larger than val
    public int searchNext(TreeNode root, int val) {
        if(root == null)
            return -1;

        if(root.left != null) {
            TreeNode node = root.left;
            while(node.right != null)
                node = node.right;

            if(node.val == val)
                return root.val;
        }

        if(root.val == val) {
            TreeNode node = root.right;

            if(node == null)
                return -1;

            while(node.left != null)
                node = node.left;
            return node.val;
        } else if(root.val > val) {
            return searchNext(root.left, val);
        } else {
            return searchNext(root.right, val);
        }
    }
}
