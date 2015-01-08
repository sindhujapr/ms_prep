public class FindKthNodeBST {
    private static class TreeNode {
        TreeNode left, right;
        int val;
        public TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = init();

        for(int i = 1; i < 7; i++)
            printk(root, i);
    }

    public static TreeNode init() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        return root;
    }

    // similar C++ solution http://se7so.blogspot.com/2014/02/how-to-prepare-for-interview-10.html
    public static void printk(TreeNode root, int k) {
        if(root == null)
            return;

        int left = count(root.left);
        if(left == k-1)
            System.out.println(root.val);
        else if(left >= k)
            printk(root.left, k);
        else
            printk(root.right, k-left-1);
    }

    private static int count(TreeNode root) {
        if(root == null)
            return 0;

        int left = count(root.left);
        int right = count(root.right);
        return left+right+1;
    }
}
