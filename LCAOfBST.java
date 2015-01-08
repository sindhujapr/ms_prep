package lc;

/*
 * http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-search-tree.html
 */
public class LCAOfBST {
    static class BSTNode {
        int value;
        BSTNode left, right;
        public BSTNode(int value) { this.value = value; }
        @Override public int hashCode() { return value * 11; }
        @Override public boolean equals(Object o) {
            if(!(o instanceof BSTNode))
                return false;
            BSTNode node = (BSTNode) o;
            return this.value == node.value;
        }
        @Override public String toString() { return Integer.toString(value); }
    }

    public static void main(String[] args) {
        BSTNode root = init();
        System.out.println(findLCA(root, new BSTNode(2), new BSTNode(5)).value);
        System.out.println(findLCA_iter(root, new BSTNode(2), new BSTNode(5)).value);
    }

    /*
     * The below two implementations assume that both nodes exist in the tree.
     */
    public static BSTNode findLCA(BSTNode node, BSTNode n1, BSTNode n2) {
        if(node == null || n1 == null || n2 == null)
            return null;
        
        if(Math.max(n1.value, n2.value) < node.value)
            return findLCA(node.left, n1, n2);
        else if(Math.min(n1.value, n2.value) > node.value)
            return findLCA(node.right, n1, n2);
        else
            return node;
    }
    
    public static BSTNode findLCA_iter(BSTNode node, BSTNode n1, BSTNode n2) {
        if(node == null || n1 == null || n2 == null)
            return null;
        
        BSTNode n = node;
        while(n != null) {
            if(Math.max(n1.value, n2.value) < n.value)
                n = n.left;
            else if(Math.min(n1.value, n2.value) > n.value)
                n = n.right;
            else
                return n;
        }
        return null;
    }
    
    public static BSTNode init() {
        BSTNode root = new BSTNode(6);
        root.left = new BSTNode(2);
        root.right = new BSTNode(8);
        
        root.left.left = new BSTNode(0);
        root.left.right = new BSTNode(4);
        root.left.right.left = new BSTNode(3);
        root.left.right.right = new BSTNode(5);
        
        root.right.left = new BSTNode(7);
        root.right.right = new BSTNode(9);
        return root;
    }
}