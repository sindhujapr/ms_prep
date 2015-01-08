package trees;

/*
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://en.wikipedia.org/wiki/Threaded_binary_tree
 * http://comsci.liu.edu/~murali/algo/Morris.htm
 * http://adtinfo.org/libavl.html/Threaded-Binary-Search-Trees.html
 */
public class ThreadedTree {
    static class TreeNode {
        private final int value;
        TreeNode left, right;
        public TreeNode(int value) { this.value = value; }
        @Override public String toString() { return Integer.toString(value); }
    }

    public static void main(String[] args) {
        TreeNode root = init();
        inorder(root);
    }
    
    /*
     * when we visit a node the first time, we will link it with its predecessor by
     * prev.right = current. Thus after we traverse it's left part (that includes its
     * predecessor), we'll be able to locate this node again. When we visit this node
     * again, we'll again check its predecessor to unlink it, print the value of this
     * node, then move to current.right. We can restore the tree after the traversal.
     * 
     * This is Morris algorithm:
     * http://gongxuns.blogspot.com/2012/12/leetcodebinary-tree-inorder-traversal.html
     */
    public static void inorder(TreeNode root) {
        TreeNode current = root;
        while(current != null) {
            // this branch is only to handle node that doesn't have left child
            if(current.left == null) {
                System.out.print(current.value + ", ");
                current = current.right;
            } else {
                TreeNode prev = current.left;
                while(prev.right != null && prev.right != current)
                    prev = prev.right;
                
                if(prev.right == null) {
                    prev.right = current;
                    current = current.left;
                } else if(prev.right == current) {
                    prev.right = null;
                    System.out.print(current.value + ", ");
                    current = current.right;
                }
            }
        }
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        root.left.left.right = new TreeNode(8);
        root.left.left.right.left = new TreeNode(9);
        
        return root;
    }
}