package oog;

/**
 * http://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
 */
public class SuccessorPredecessorBST {
    public static void main(String[] args) {
        Node root = init();

        findPreSuc(root, 6);

        System.out.println(pre != null ? pre.key : null);
        System.out.println(suc != null ? suc.key : null);

        findPreSuc(root, 10);

        System.out.println(pre != null ? pre.key : null);
        System.out.println(suc != null ? suc.key : null);
    }

    public static Node init() {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(7);

        root.left.left = new Node(1);
        root.left.right = new Node(1);

        root.right.left = new Node(6);
        root.right.right = new Node(9);
        return root;
    }

    private static Node pre;
    private static Node suc;

    // set the last node as pre if key doesn't exist in the BST
    public static void findPreSuc(Node root, int key) {
        if (root == null)  return ;

        // If key is present at root
        if (root.key == key) {
            // the maximum value in left subtree is predecessor
            if (root.left != null) {
                Node tmp = root.left;
                while(tmp.right != null)
                    tmp = tmp.right;
                pre = tmp ;
            }

            // the minimum value in right subtree is successor
            if (root.right != null) {
                Node tmp = root.right ;
                while (tmp.left != null)
                    tmp = tmp.left ;
                suc = tmp ;
            }
            return ;
        }

        // If key is smaller than root's key, go to left subtree
        if (root.key > key) {
            suc = root;
            findPreSuc(root.left, key);
        } else {
            pre = root;
            findPreSuc(root.right, key);
        }
    }

    private static class Node {
        int key;
        Node left, right;
        public Node(int val) { this.key = val; }
    }
}
