package careercup.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=15645665
 */
public class KthNodeInBST {
    static class Node {
        int value;
        Node left, right;
        public Node(int value) { this.value = value; }
        @Override public String toString() { return Integer.toString(value); }
    }
    
    public static void main(String[] args) {
        Node root = init();
        System.out.println(findKth(root, 2).value + "\t" + findKth_iter(root, 2).value);
        count = 0;
        System.out.println(findKth(root, 3).value + "\t" + findKth_iter(root, 3).value);
        count = 0;
        System.out.println(findKth(root, 5).value + "\t" + findKth_iter(root, 5).value);
    }
    
    public static Node init() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        
        root.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(7);
        return root;
    }
    
    private static int count = 0;
    public static Node findKth(Node node, int K) {
        if(node == null)
            return null;
        
        Node right = findKth(node.right, K);
        
        if(right != null)
            return right;
        
        if(++count == K)
            return node;
        
        return findKth(node.left, K);
    }
    
    public static Node findKth_iter(Node node, int K) {
        List<Node> stack = new ArrayList<Node>();
        Map<Node, Integer> map = new HashMap<Node, Integer>();
        
        stack.add(node);
        map.put(node, 1);

        /*
         * The first time we access the node: insert it into the stack as a child
         * 2nd time: pop it and insert its children
         * 3nd time: we can count it
         */
        int cnt = 0;
        while(stack.size() > 0) {
            Node top = stack.remove(stack.size()-1);
            if(map.containsKey(top) && map.get(top) == 2) {
                if(++cnt == K)
                    return top;
                continue;
            }
            
            if(top.left != null) {
                stack.add(top.left);
                map.put(top.left, 1);
            }
            
            stack.add(top);
            map.put(top, 2);

            if(top.right != null) {
                stack.add(top.right);
                map.put(top.right, 1);
            }
        }
        
        return null;
    }
}