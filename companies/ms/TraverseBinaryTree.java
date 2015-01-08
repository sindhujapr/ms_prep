package interview.ms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *    10
 *   /  \
 *  6    14
 * /    / \
 *4    12 16
 */

public class TraverseBinaryTree {
    private BSTNode root = null;

    private TraverseBinaryTree insert(int value) {
    if (root == null) {
        root = new BSTNode(value);
        return this;
    }

    BSTNode node = findPlaceToInsert(value);
    BSTNode newNode = new BSTNode(value);
    if (node.value == value)
        return this;
    else if (node.value > value) {
        node.lc = newNode;
        newNode.parent = node;
    } else {
        node.rc = newNode;
        newNode.parent = node;
    }

    return this;
    }

    private BSTNode findPlaceToInsert(int value) {
    BSTNode node = root;
    while (node != null) {
        if (node.value == value) {
        return node;
        }

        if (node.value > value) {
        if (node.lc != null)
            node = node.lc;
        else
            break;
        }

        if (node.value < value) {
        if (node.rc != null)
            node = node.rc;
        else
            break;
        }
    }

    return node;
    }

    public void check() {
    if (check(root))
        System.out.println("valid binary search tree");
    else
        System.out.println("invalid binary search tree");
    }

    /*
     * check whether the tree starts from the current node is a valid BST
     */
    public boolean check(BSTNode node) {
    if (node == null)
        return true;

    if (node.lc != null && node.value < node.lc.value) {
        return false;
    }

    if (node.rc != null && node.value > node.rc.value)
        return false;

    return check(node.lc) & check(node.rc);
    }

    public void init() {
    insert(10).insert(6).insert(12).insert(14);
    insert(5).insert(4).insert(11).insert(9);
    insert(7).insert(8).insert(15).insert(16);
    }

    public static void main(String[] args) {
    TraverseBinaryTree instance = new TraverseBinaryTree();
    instance.init();
    instance.check();

    System.out.println(instance.inOrder1());
    System.out.println(instance.inOrder2());
    }

    /*
     * traverse without recursion
     */
    public List<BSTNode> inOrder1() {
    Map<BSTNode, Integer> map = new HashMap<BSTNode, Integer>();
    
    List<BSTNode> stack = new ArrayList<BSTNode>();
    stack.add(root);
    map.put(root, 1);

    List<BSTNode> result = new ArrayList<BSTNode>();
    
    while(stack.size() > 0) {
        BSTNode node = stack.remove(stack.size()-1);
        
        /*
         * when the node is first added to the stack, the counter is set to 1.
         * when the node is popped from the stack and added again to the stack,
         * the counter is set to 2.
         */
        if(map.get(node) == 2) {
        result.add(node);
        /*
         * once the node has been added to the result set, we can remove it
         * from the map to save memory footprint.
         */
        map.remove(node);
        } else {
        if(node.rc != null) {
            stack.add(node.rc);
            map.put(node.rc, 1);
        }
        
        stack.add(node);
        map.put(node, 2);

        if(node.lc != null) {
            stack.add(node.lc);
            map.put(node.lc, 1);
        }
        
        }
    }
    
    return result;
    }
    
    /*
     * we need to use parent pointer in this routine
     */
    public List<BSTNode> inOrder2() {
    List<BSTNode> result = new ArrayList<BSTNode>();

    /*
     * we shouldn't start from the root node. Instead, we should start
     * from the smallest value in the tree, which can be returned by 
     * passing null to findNext() routine.
     */
    BSTNode node = findNext(null);
    while(node != null) {
        result.add(node);
        node = findNext(node);
    }
    
    return result;
    }

    private BSTNode findNext(BSTNode node) {
    if (node == null) {
        BSTNode next = root;
        while (next != null) {
        if (next.lc == null)
            break;
        else
            next = next.lc;
        }

        return next;
    }

    /*
     * first find the smallest in the rc
     */
    if (node.rc != null) {
        BSTNode next = node.rc;
        while (next.lc != null) {
        next = next.lc;
        }
        return next;
    }

    BSTNode parent = node.parent;
    if (node == parent.lc)
        return parent;

    while (node.parent != null && node == node.parent.rc)
        node = node.parent;

    return node.parent;
    }
}