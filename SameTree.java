package lc2;

import java.util.LinkedList;
import java.util.List;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        else if((p == null && q != null) || (p != null && q == null))
            return false;
        else
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        // Start typing your Java solution below
        // DO NOT write main() function
        List<TreeNode> l1 = new LinkedList<TreeNode>();
        List<TreeNode> l2 = new LinkedList<TreeNode>();
        
        l1.add(p);
        l2.add(q);
        while(!l1.isEmpty() && !l2.isEmpty()) {
            TreeNode n1 = l1.remove(0);
            TreeNode n2 = l2.remove(0);
            
            if((n1 == null && n2 != null) ||
                (n1 != null && n2 == null) ||
                (n1 != null && n2 != null && n1.val != n2.val))
                return false;
            
            if(n1 != null && n2 != null) {
                l1.add(n1.left);
                l1.add(n1.right);
                l2.add(n2.left);
                l2.add(n2.right);
            }
        }
        return true;
    }
}