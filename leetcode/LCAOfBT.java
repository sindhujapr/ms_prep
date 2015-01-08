package lc;

/*
 * http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
 */
public class LCAOfBT {
    static class Node {
        int value;
        Node left, right;
        public Node(int value) { this.value = value; }
        @Override public int hashCode() { return value * 11; }
        @Override public boolean equals(Object o) {
            if(!(o instanceof Node))
                return false;
            Node node = (Node) o;
            return this.value == node.value;
        }
        @Override public String toString() { return Integer.toString(value); }
    }
    
    public static void main(String[] args) {
        Node root = init();
        System.out.println(findLCA(root, new Node(2), new Node(5)).value);
    }

    /*
     * bottom-up is better than top-down to solve this problem
     * 
     * However, this implementation still doesn't check whether the
     * two nodes exists in the binary tree.
     */
    public static Node findLCA(Node node, Node n1, Node n2) {
        if(node == null)
            return null;
        if(node == n1 || node == n2)
            return node;
        
        Node left = findLCA(node.left, n1, n2);
        Node right = findLCA(node.right, n1, n2);
        // found one from left and the other from right
        if(left != null && right != null)
            return node;
        
        return left != null ? left : right;
    }

    public static Node init() {
        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);
        
        root.right.left = new Node(7);
        root.right.right = new Node(9);
        return root;
    }
}
