package others;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    TreeNode left, right;
    int val;
    public TreeNode(int val) { this.val = val; }
}

/*
 * http://whiteboardalgo.blogspot.com/2012/08/tree-algorithms.html
 */
public class CloneTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        
        root.right.left.right = new TreeNode(6);
        
        TreeNode newRoot1 = clone(root);
        System.out.println(equals(root, newRoot1));
        
        TreeNode newRoot2 = clone2(root);
        System.out.println(equals(root, newRoot2));
    }
    
    public static TreeNode clone(TreeNode root) {
        if(root == null)
            return null;
    
        List<TreeNode> s1 = new ArrayList<TreeNode>();
        List<TreeNode> s2 = new ArrayList<TreeNode>();
        s1.add(root);
        
        TreeNode newRoot = new TreeNode(root.val);
        s2.add(newRoot);
        
        while(s1.size() > 0) {
            TreeNode n1 = s1.remove(s1.size()-1);
            TreeNode n2 = s2.remove(s2.size()-1);
            
            if(n1.left != null) {
                s1.add(n1.left);
                n2.left = new TreeNode(n1.left.val);
                s2.add(n2.left);
            }
            
            if(n1.right != null) {
                s1.add(n1.right);
                n2.right = new TreeNode(n1.right.val);
                s2.add(n2.right);
            }
        }
        
        return newRoot;
    }
    
    public static TreeNode clone2(TreeNode root) {
        if(root == null)
            return null;
    
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = clone2(root.left);
        newRoot.right = clone2(root.right);
        return newRoot;
    }
    
    public static boolean equals(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)
            return true;
        
        if((root1 == null && root2 != null) ||
            (root1 != null && root2 == null) ||
            (root1.val != root2.val))
            return false;
        
        if(!equals(root1.left, root2.left) | !equals(root1.right, root2.right))
            return false;
        
        return true;
    }
}