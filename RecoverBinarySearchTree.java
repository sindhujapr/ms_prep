package lc;

public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(1);
        new RecoverBinarySearchTree().recoverTree(root);
    }

    /*
     * I don't understand.
     * http://gongxuns.blogspot.com/2012/12/leecoderecover-binary-search-tree.html
     */
    public void recoverTree(TreeNode root) {
        TreeNode n1 = null, n2 = null;

        TreeNode current = root, prev = null, a = null;
        boolean first = true;
        while (current != null) {
            if (current.left == null) {
                if (a != null && a.val > current.val) {
                    if (first) {
                        n1 = a;
                        first = false;
                    } else {
                        n2 = current;
                    }
                }
                if (n1 != null && a == n1)
                    n2 = current;
                a = current;
                current = current.right;
            } else {
                prev = current.left;
                while (prev.right != null && prev.right != current)
                    prev = prev.right;

                if (prev.right == null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    if (a != null && a.val > current.val) {
                        if (first) {
                            n1 = a;
                            first = false;
                        } else {
                            n2 = current;
                        }
                    }
                    if (n1 != null && a == n1)
                        n2 = current;
                    a = current;
                    prev.right = null;
                    current = current.right;
                }
            }
        }

        assert (n1 != null && n2 != null);
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    /*
     * http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
     * I rewrote it in Java but don't know why it doesn't work with OJ (produces
     * weird result), though it works with my JVM.
     * 
     * It works now as of 02/05/2014
     */
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree1(TreeNode root) {
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private void inorder(TreeNode node) {
        if (node == null)
            return;

        inorder(node.left);

        if (prev != null && prev.val > node.val) {
            if (first == null)
                first = prev;
            /*
             * This is an edge case. It's possible we have only two elements in the tree
             * and they are out of order. In this case, we should anyway assign second
             * as node.
             */
            second = node;
        }
        prev = node;

        inorder(node.right);
    }
}
