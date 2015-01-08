package google;

/**
 * Created by qingcunz on 12/24/14.
 */
public class TreeIterator {
    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) { this.val = val; }
        @Override public String toString() { return Integer.toString(val); }
    }


    public static void main(String[] args) {
        TreeIterator ins = new TreeIterator(init());
        while(ins.hasNext())
            System.out.print(ins.next() + "\t");
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(11);

        root.left.left.right = new TreeNode(4);

        root.right.right = new TreeNode(10);
        root.right.left = new TreeNode(7);
        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(8);
        return root;
    }

//    private List<TreeNode> stack = new ArrayList<TreeNode>();
//
//    public TreeIterator(TreeNode root) {
//        TreeNode node = root;
//        while(node != null) {
//            stack.add(node);
//            node = node.left;
//        }
//    }
//
//    // time: O(nlgn) ?
//    public boolean hasNext() {
//        return stack.size() > 0;
//    }
//
//    public int next() {
//        TreeNode node = stack.remove(stack.size()-1);
//        TreeNode left = node.right;
//        while(left != null) {
//            stack.add(left);
//            left = left.left;
//        }
//
//        return node.val;
//    }

    private TreeNode curr = null;
    public TreeIterator(TreeNode root) {
        curr = root;
    }

    // morris
    public boolean hasNext() {
        return curr != null;
    }

    public int next() {
        while(curr.left != null) {
            TreeNode temp = curr.left;

            // check if the left subtree has been visited
            while (temp.right != null && temp.right != curr)
                temp = temp.right;

            if (temp.right == curr) {
                /*
                 * left part has been visited:
                 * return current value and move to right child
                 */
                temp.right = null;
                break;
            } else {
                /*
                 * left part hasn't been visited:
                 * link the previous node to the current node and continue to the left most node
                 */
                temp.right = curr;
                curr = curr.left;
            }
        }

        int val = curr.val;
        curr = curr.right;
        return val;
    }
}