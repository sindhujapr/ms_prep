package trees;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private static int counter = 0;
    private final int id = counter++;

    // This is constrained by the degree
    private int keyNum = 0;

    private final List<Integer> keys = new ArrayList<Integer>();
    /*
     * Leaves contain real values. Others contain pointer to leaves.
     */
    private final List<TreeNode> children = new ArrayList<TreeNode>();

    public int findKeyIndex(int key) {
    return keys.indexOf(key);
    }

    public int findChildIndex(TreeNode child) {
    return children.indexOf(child);
    }

    public Integer getKey(int index) {
    if (index >= keyNum)
        return null;
    else
        return keys.get(index);
    }

    public int getKeyNum() {
    return keyNum;
    }

    public List<Integer> getKeys() {
    return keys;
    }

    public void addKey(int i, int key) {
    keys.add(i, key);
    keyNum++;
    }

    public void addKey(int key) {
    keys.add(key);
    keyNum++;
    }

    public int getChildNum() {
    return children.size();
    }

    public TreeNode getChild(int i) {
    return children.get(i);
    }

    public List<TreeNode> getChildren() {
    return children;
    }

    public void addChild(int i, TreeNode node) {
    children.add(i, node);
    }

    public void addChild(TreeNode child) {
    children.add(child);
    }

    public void removeChild(TreeNode child) {
    if (children.contains(child)) {
        children.remove(child);
    }
    }

    public boolean containChild(TreeNode child) {
    return children.contains(child);
    }

    public int countKeys() {
    int total = keyNum;

    for (TreeNode child : children)
        total += child.countKeys();

    return total;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    for (Integer key : keys) {
        sb.append(key);
        sb.append(" ");
    }
    sb.append("]");

    return sb.toString();
    }
}