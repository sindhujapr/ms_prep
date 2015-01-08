package lc2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SymmetricTree {
	public static void main(String[] args) {
		char[] chs = "my name is simon".toCharArray();
		int index = 0;
		while(true) {
			char ch = chs[chs.length-1];
			for(int i = chs.length-1; i >= 0 && chs[i] != ' '; i--)
				chs[i] = chs[i-1];
			chs[index] = ch;
			
		}
	}
    public boolean isSymmetric3(TreeNode root) {
        if(root == null)
            return true;
        
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        while(true) {
            ArrayList<TreeNode> temp = new ArrayList<TreeNode>();
            boolean allNull = true;
            int size = list.size();
            for(int i = 0; i < size; i++) {
                TreeNode n1 = list.get(i), n2 = list.get(size-i-1);
                if(n1 == null && n2 == null)
                    continue;
                else if((n1 == null && n2 != null) ||
                    (n1 != null && n2 == null) || n1.val != n2.val)
                    return false;
                else {
                    temp.add(n1.left);
                    temp.add(n1.right);
                    allNull = false;
                }
                
            }
            if(allNull)
                break;
            
            list = temp;
        }
        
        return true;
    }
    
	/*
	 * http://gongxuns.blogspot.com/2012/12/leetcodesymmetric-tree.html
	 */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        
        /*
         * use only one container for each half
         */
        List<TreeNode> left = new LinkedList<TreeNode>();
        List<TreeNode> right = new LinkedList<TreeNode>();
        left.add(root.left);
        right.add(root.right);
        
        while(left.size() > 0 && right.size() > 0) {
            TreeNode l = left.remove(0);
            TreeNode r = right.remove(0);
            if((l == null && r != null) ||
                (l != null && r == null) ||
                (l != null && r != null && r.val != l.val))
                return false;
            
            if(l != null && r != null) {
                left.add(l.left);
                left.add(l.right);
                right.add(r.right);
                right.add(r.left);
            }
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;
        
        return symmetric(root.left, root.right);
    }
    
    private boolean symmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null)
            return true;
        else if((left == null && right != null) || (left != null && right == null))
            return false;
        else
        	return left.val == right.val && symmetric(left.left, right.right) &&
                symmetric(left.right, right.left);
    }
}