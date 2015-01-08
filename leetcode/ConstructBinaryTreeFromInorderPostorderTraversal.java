package lc2;

public class ConstructBinaryTreeFromInorderPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        return build(inorder, 0, len - 1, postorder, 0, len - 1);
    }

    private TreeNode build(int[] in, int s1, int e1, int[] post, int s2, int e2) {
        if (s1 > e1)
            return null;

        TreeNode root = new TreeNode(post[e2]);
        int index = e1;
        while (index >= s1) {
            if (in[index] == post[e2])
                break;
            index--;
        }

        root.left = build(in, s1, index - 1, post, s2, e2 - (e1 - index) - 1);
        root.right = build(in, index + 1, e1, post, e2 - (e1 - index), e2 - 1);
        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;

        return buildTree(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int start1, int end1, int[] postorder, int lastIndex) {
        /*
         * the second condition is also necessary
         */
        if (lastIndex < 0 || start1 > end1)
            return null;

        int midVal = postorder[lastIndex];
        TreeNode node = new TreeNode(midVal);

        // no duplicate
        int index = indexOf(inorder, midVal);
        TreeNode right = buildTree(inorder, index + 1, end1, postorder, lastIndex - 1);
        if (right != null)
            node.right = right;

        /*
         * here we need to calculate the elements for the left subtree.
         */
        int index2 = lastIndex - (end1 - index) - 1;
        TreeNode left = buildTree(inorder, start1, index - 1, postorder, index2);
        if (left != null)
            node.left = left;

        return node;
    }

    private int indexOf(int[] array, int val) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == val)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderPostorderTraversal instance = new ConstructBinaryTreeFromInorderPostorderTraversal();
        instance.buildTree(new int[] { 2, 1 }, new int[] { 2, 1 });
    }
}
