package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversalII {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if(root == null)
            return result;

        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(root);
        
        levelOrderBottom(nodes, result);
        return result;
    }
    
    private void levelOrderBottom(List<TreeNode> nodes, ArrayList<ArrayList<Integer>> result) {
        if(nodes.size() == 0)
            return;

        ArrayList<Integer> values = new ArrayList<Integer>();        
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        
        /*
         * construct the list containing the nodes of the child level,
         * and meanwhile, keep the values of the current level. 
         */
        for(TreeNode node : nodes) {
            if(node.left != null) {
                childNodes.add(node.left);
            }

            if(node.right != null) {
                childNodes.add(node.right);
            }
            
            values.add(node.val);
        }
        
        levelOrderBottom(childNodes, result);
        result.add(values);
    }
}