// http://www.mitbbs.com/article_t/JobHunting/32812179.html
public class SearchNextInTree {
    // search node whose value is larger then the given one. O(lgn)
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

            // this is a corner case. if the node is right-most one
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
