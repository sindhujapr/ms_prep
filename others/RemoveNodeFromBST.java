package others;

import java.util.Arrays;

public class RemoveNodeFromBST {
    public static void main(String[] args) {
        int[] array = {3, 5, 1, 9, 8, 2, 6, 4, 10, 7};
        TreeNode root = buildBST(array);
        
        while(root != null) {
            System.out.println("deleting " + root.val);
            root = deleteNode(root);
            System.out.println(isBST(root));
        }
    }
    
    public static TreeNode buildBST(int[] array) {
        Arrays.sort(array);
        
        return buildBST1(array, 0, array.length-1);
    }
    
    private static TreeNode buildBST1(int[] array, int start, int end) {
        if(start > end)
            return null;
        
        int mid = (start+end) >> 1;
        TreeNode node = new TreeNode(array[mid]);
        node.left = buildBST1(array, start, mid-1);
        node.right = buildBST1(array, mid+1, end);
        return node;
    }
    
    /*
     * verify whether a tree is binary search tree
     */
    public static boolean isBST(TreeNode node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean isBST(TreeNode node, int min, int max) {
        if(node == null)
            return true;
        
        if(node.val > max || node.val < min)
            return false;
        
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }
    
    /*
     * delete the root node of the tree
     */
    public static TreeNode deleteNode(TreeNode node) {
        if(node == null)
            return null;
        
        if(node.left == null && node.right == null)
            return null;
        
        if(node.left == null)
            return node.right;
        if(node.right == null)
            return node.left;
        
        // find the successor of the node to replace it
        TreeNode right = node.right;
        if(right.left == null) {
            right.left = node.left;
            return right;
        }
            
        TreeNode prev = right;
        TreeNode successor = right.left;
        while(successor.left != null) {
            prev = successor;
            successor = successor.left;
        }
        
        prev.left = successor.right;
        successor.left = node.left;
        successor.right = node.right;
        return successor;
    }
}