package lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSumII {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return result;

        ArrayList<Integer> one = new ArrayList<Integer>();
        Map<TreeNode, ArrayList<Integer>> map = new HashMap<TreeNode, ArrayList<Integer>>();
        one.add(root.val);
        map.put(root, one);

        while(map.size() > 0) {
            Map<TreeNode, ArrayList<Integer>> temp = new HashMap<TreeNode, ArrayList<Integer>>();
            for(TreeNode node : map.keySet()) {
                if(node.left == null && node.right == null) {
                    int count = 0;
                    for(Integer v : map.get(node))
                        count += v;
                    if(count == sum)
                        result.add(map.get(node));
                } else {
                    ArrayList<Integer> values = map.get(node);
                    if(node.left != null) {
                        ArrayList<Integer> left = new ArrayList<Integer>(values);
                        left.add(node.left.val);
                        temp.put(node.left, left);
                    }
                    
                    if(node.right != null) {
                        values.add(node.right.val);
                        temp.put(node.right, values);
                    }
                }
            }
            map = temp;
        }
        
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        pathSum(root, sum, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void pathSum(TreeNode root, int sum, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> res) {
        if(root == null)
            return;
        
        list.add(root.val);
        if(root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<Integer>(list));
        } else {
            pathSum(root.left, sum-root.val, list, res);
            pathSum(root.right, sum-root.val, list, res);
        }
        list.remove(list.size()-1);
    }
    
    /*
     * doesn't pass large judge due to time limit
     */
    public ArrayList<ArrayList<Integer>> pathSum3(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return result;

        ArrayList<Integer> one = new ArrayList<Integer>();
        List<TreeNode> stack = new ArrayList<TreeNode>();
        one.add(root.val);
        stack.add(root);
        sum -= root.val;
        TreeNode node = root.left != null ? root.left : root.right;
        
        do {
            while(node != null) {
                stack.add(node);
                one.add(node.val);
                sum -= node.val;
                node = node.left != null ? node.left : node.right;
            }
            
            TreeNode last = stack.remove(stack.size()-1);
            if(node == null && sum == 0 && last.left == null && last.right == null) {
                result.add(new ArrayList<Integer>(one));
            }
            
            sum += one.remove(one.size()-1);
            if(stack.size() > 0 && last == stack.get(stack.size()-1).left)
                node = stack.get(stack.size()-1).right;
            // else node remains null
        } while(node != null || stack.size() > 1);
        
        return result;
    }
}
