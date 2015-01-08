package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=5632735657852928
 */
class BST {
    int value;
    BST left, right;
}

public class CheckBST {
    public static void main(String[] args) {
        
    }
    
    public static boolean isBST(BST node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static boolean isBST(BST node, int low, int high) {
        if(node == null)
            return true;
        
        if(node.value > high || node.value < low)
            return false;
        
        return isBST(node.left, low, node.value) &&
                isBST(node.right, node.value, high);
    }
    
    public static boolean isBST_iterative(BST node) {
        if(node == null)
            return true;
        
        List<BST> stack = new ArrayList<BST>();
        List<Integer> values = new ArrayList<Integer>();
        stack.add(node);
        values.add(Integer.MIN_VALUE);
        values.add(Integer.MAX_VALUE);

        while(stack.size() > 0) {
            BST bst = stack.remove(stack.size()-1);
            int high = values.remove(values.size()-1);
            int low = values.remove(values.size()-1);
            if(bst.value > high || bst.value < low)
                return false;
            
            // left child
            if(bst.left != null) {
                stack.add(bst.left);
                values.add(low);
                values.add(bst.value);
            }
            
            if(bst.right != null) {
                stack.add(bst.right);
                values.add(bst.value);
                values.add(high);
            }
        }
        
        return true;
    }
}