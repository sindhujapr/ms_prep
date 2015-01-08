package trees;

public class TreeDeepCopy {
    static class TreeNode {
        private final int value;
        private TreeNode left, right;
        public TreeNode(TreeNode left, int value, TreeNode right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
        
        public TreeNode deepCopy(TreeNode node) {
            if(node == null)
                return null;

            TreeNode left = deepCopy(node.left);
            TreeNode right = deepCopy(node.right);
            return new TreeNode(left, node.value, right);
        }
    }
}