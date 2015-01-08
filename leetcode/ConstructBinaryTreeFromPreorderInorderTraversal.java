package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ConstructBinaryTreeFromPreorderInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0)
            return null;
        
        return buildTree(preorder, 0, inorder, 0, inorder.length-1);
    }
    
    private TreeNode buildTree(int[] preorder, int index, int[] inorder, int start, int end) {
        if(index < 0 || index >= preorder.length || start > end)
            return null;
        
        int midVal = preorder[index];
        TreeNode node = new TreeNode(midVal);
        
        int midIndex = indexOf(inorder, midVal);
        TreeNode left = buildTree(preorder, index+1, inorder, start, midIndex-1);
        if(left != null)
            node.left = left;

        int rightIndex = index+(midIndex-start)+1;
        TreeNode right = buildTree(preorder, rightIndex, inorder, midIndex+1, end);
        if(right != null)
            node.right = right;
        
        return node;
    }
    
    private int indexOf(int[] array, int value) {
        for(int i = 0; i < array.length; i++)
            if(array[i] == value)
                return i;
        return -1;
    }
    
    public static void main(String[] args) {
    ConstructBinaryTreeFromPreorderInorderTraversal instance = new ConstructBinaryTreeFromPreorderInorderTraversal();
    instance.buildTree(new int[] { 2,1 }, new int[] { 1,2 });
    
    List<Integer> list = new ArrayList<Integer>();
    list.add(null);
    list.add(null);
    System.out.println(list.size());
    }
}