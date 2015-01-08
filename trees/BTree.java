package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Definition of B-Tree:
 * 1) Every node in the tree has at most <degree> (degree >= 2) children
 * 2) Except root and leaves, each node has at least ceil(m/2) children
 * 3) If root is not leaf, it has at least 2 children
 * 4) All leaves are at the same level and have no children
 * 5) C0, K1, C1, K2, ..., Kn, Pn
 */
public class BTree {
    // better to be odd number
    private static final int DEGREE = 5;
    private TreeNode root = new TreeNode();

    // Suppose no duplicate
    public TreeNode findNodeToInsert(int key) {
    // each node can have at most DEGREE-1 keys
    TreeNode node = root;
    List<Integer> keys = node.getKeys();
    int keyNum = node.getKeyNum();
    List<TreeNode> children = node.getChildren();

    /*
     * find the right node to insert the key: 1) find a leaf node that has
     * no child
     */
    while (children.size() > 0) {
        int i = 0;
        for (i = 0; i < keyNum; i++) {
        int element = keys.get(i);
        if (element > key)
            break;
        }
        node = children.get(i);
        keys = node.getKeys();
        keyNum = node.getKeyNum();
        children = node.getChildren();
    }

    return node;
    }

    public TreeNode findKey(int key) {
    TreeNode node = root;
    List<Integer> keys = node.getKeys();
    int keyNum = node.getKeyNum();
    List<TreeNode> children = node.getChildren();

    while (keys.size() > 0) {
        int i = 0;
        for (; i < keyNum; i++) {
        int element = keys.get(i);
        if (element == key)
            return node;

        if (element > key)
            break;
        }

        if (children.size() > i) {
        node = children.get(i);
        keys = node.getKeys();
        keyNum = node.getKeyNum();
        children = node.getChildren();
        } else {
        return null;
        }
    }

    return null;
    }

    public TreeNode findParentNode(int key) {
    TreeNode parent = null;

    TreeNode node = root;
    List<Integer> keys = node.getKeys();
    List<TreeNode> children = node.getChildren();

    while (children.size() > 0) {
        if (keys.contains(keys))
        return parent;

        int keyNum = node.getKeyNum();
        int i = 0;
        for (i = 0; i < keyNum; i++) {
        int element = keys.get(i);
        if (element == key)
            return parent;
        if (element > key)
            break;
        }
        parent = node;
        node = children.get(i);
        keys = node.getKeys();
        keyNum = node.getKeyNum();
        children = node.getChildren();
    }

    return parent;
    }

    /*
     * Different situations: 1) node has enough keys and has no child, remove
     * directly 2) node doesn't have enough keys and has child, move up a key
     * from child who has enough keys. if no child has enough keys, ... 3) node
     * doesn't have enough keys and has no child, move down a key from parent
     * node, then move a key from the child who has most keys to parent node 4)
     * node doesn't have enough keys and has no child, also no brother node has
     * enough keys, then move down a key in parent node and combine it with a
     * brother node. But here it's possible that the parent node doesn't have
     * enough keys, two situations: 4a) if a brother node has enough keys, move
     * down a key from parent node and move up a key from the brother node, of
     * course one child of the brother node needs to be moved 4b) if no brother
     * node has enough keys, move down the key from parent node and combine the
     * node with brother node
     */
    public BTree remove(int key) {
    TreeNode node = findKey(key);
    TreeNode parentNode = findParentNode(key);
    if (node == null)
        return this;

    List<Integer> keys = node.getKeys();
    List<TreeNode> children = node.getChildren();

    int index = node.findKeyIndex(key);
    keys.remove(new Integer(key));

    // no child node
    if (node.getChildNum() == 0) {
        // try to borrow one from the brother node
        if (keys.size() < DEGREE / 2) {
        List<Integer> parentKeys = parentNode.getKeys();
        int parentKeyToMove = parentKeys.get(index);
        keys.add(parentKeyToMove);
        parentKeys.remove(index);

        // parent node may node have enough keys now
        // check if we can move up one from adjacent node
        // otherwise we have to combine with the adjacent node
        int nodeIndex = parentNode.getChildren().indexOf(node);
        TreeNode brotherNode = parentNode.getChildren().get(
            nodeIndex + 1);
        List<Integer> brotherKeys = brotherNode.getKeys();
        if (keys.size() + brotherKeys.size() < DEGREE) {
            // combine

        } else {
            // move one key from brother node to the parent
        }
        }

        return this;
    }

    // node has child
    TreeNode lNode = node.getChild(index);
    TreeNode rNode = node.getChild(index + 1);
    List<Integer> lcKeys = lNode.getKeys();
    List<Integer> rcKeys = rNode.getKeys();

    // but the child nodes don't have enough keys
    if (lcKeys.size() < DEGREE / 2 && rcKeys.size() < DEGREE / 2) {
        for (int i = 0; i < lcKeys.size(); i++) {
        rcKeys.add(i, lcKeys.get(i));
        }
        children.remove(index);

        return this;
    }

    // at least one child node has enough keys
    int keyToMoveup;
    TreeNode nodeToOperate;
    if (lcKeys.size() > rcKeys.size()) {
        // move up a key from left child
        keyToMoveup = lcKeys.remove(lcKeys.size() - 1);
        nodeToOperate = lNode;
    } else {
        // move up a key from right child
        keyToMoveup = rcKeys.remove(0);
        nodeToOperate = rNode;
    }
    keys.add(index, keyToMoveup);

    // we have to combine child nodes
    if (lcKeys.size() < DEGREE / 2 && rcKeys.size() < DEGREE / 2) {

    } else {
    }

    return this;
    }

    public BTree insert(int key) {
    TreeNode node = findNodeToInsert(key);
    // if duplicate key found
    if (node == null) {
        return this;
    }

    List<Integer> keys = node.getKeys();
    int keyNum = node.getKeyNum();
    List<TreeNode> children = node.getChildren();

    if (keyNum < DEGREE - 1) {
        int i = 0;
        while (keyNum > 0 && i < keyNum && keys.get(i) < key)
        i++;
        if (keys.size() == 0)
        node.addKey(key);
        else
        node.addKey(i, key);
    } else {
        TreeNode parent = findParentNode(key);

        // find out the element to be elevated
        int i = 0;
        while (i < keyNum && keys.get(i) < key)
        i++;
        keys.add(i, key);

        do {

        // index of the key to be elevated
        int pivot = DEGREE / 2;
        TreeNode lc = new TreeNode();
        for (int j = 0; j < pivot; j++) {
            lc.addKey(keys.get(j));
            if (j < children.size() && children.get(j) != null)
            lc.addChild(children.get(j));
        }
        if (pivot < children.size() && children.get(pivot) != null)
            lc.addChild(children.get(pivot));

        TreeNode rc = new TreeNode();
        for (int j = pivot + 1; j < keyNum + 1; j++) {
            rc.addKey(keys.get(j));
            if (j < children.size() && children.get(j) != null)
            rc.addChild(children.get(j));
        }
        if (keyNum + 1 < children.size()
            && children.get(keyNum + 1) != null)
            rc.addChild(children.get(keyNum + 1));

        if (parent == null)
            parent = new TreeNode();
        List<Integer> parentKeys = parent.getKeys();
        int j = 0;
        while (j < parentKeys.size()
            && parentKeys.get(j) < keys.get(pivot))
            j++;
        parent.addKey(j, keys.get(pivot));
        parent.addChild(j, lc);
        parent.addChild(j + 1, rc);
        // the current node has been split and we need to remove the
        // original one
        parent.removeChild(node);

        keys = parent.getKeys();
        children = parent.getChildren();
        node = parent;

        parent = findParentNode(keys.get(0));
        if (parent == null)
            root = node;
        } while (keys.size() > DEGREE - 1);

    }

    return this;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    List<TreeNode> children = new ArrayList<TreeNode>();
    children.add(root);

    while (children.size() > 0) {
        List<TreeNode> tmp = new ArrayList<TreeNode>();

        int index = 0;
        while (index < children.size()) {
        TreeNode node = children.get(index);
        sb.append(node.getKeys() + "\t");
        if (node.getChildNum() > 0) {
            tmp.addAll(node.getChildren());
        }
        index++;
        }
        sb.append("\n");
        children = tmp;
    }

    return sb.toString();
    }

    public int countKeys() {
    return root.countKeys();
    }

    public boolean checkTree(int count) {
    for (int i = 1; i <= count; i++) {
        if (findKey(i) == null) {
        System.err.println("error");
        return false;
        }
    }

    if (countKeys() != count) {
        System.err.println("incorrect number of keys");
        return false;
    }

    if (findKey(Integer.MIN_VALUE) != null) {
        System.err.println("wrong key found!");
        return false;
    }

    return true;
    }

    public static void main(String[] args) {
    int count = 40;

    List<Integer> list = new ArrayList<Integer>();
    for (int i = 1; i <= count; i++) {
        list.add(i);
    }
    Collections.shuffle(list);

    BTree tree1 = new BTree();

    for (int i = 0; i < list.size(); i++) {
        tree1.insert(list.get(i));
    }
    System.out.println(tree1);
    if (tree1.checkTree(count))
        System.out.println("check successful");

    tree1.remove(16);
    System.out.println(tree1);

    // BTree tree = new BTree();
    // tree.insert('A').insert('C').insert('G').insert('N');
    // tree.insert('H').insert('E').insert('K').insert('Q');
    // tree.insert('M').insert('F').insert('W').insert('L');
    // tree.insert('T').insert('Z').insert('D').insert('P');
    // tree.insert('R').insert('X').insert('Y');
    // tree.insert('S').insert('O');
    // System.out.println(tree);
    }
}