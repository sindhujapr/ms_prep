package interview.ms;

import java.util.ArrayList;
import java.util.List;

/*
 * 求二叉树中节点的最大距离...
 * 如果我们把二叉树看成一个图，
 * 父子节点之间的连线看成是双向的，
 * 我们姑且定义"距离"为两节点之间边的个数。
 * 写一个程序，
 * 求一棵二叉树中相距最远的两个节点之间的距离。
 * 
 * we only need to find out the deepest children from the lc and rc, respectively.
 */
public class FindLongestDistanceInBinaryTree {
    private BSTNode root = null;

    public int findDeepestLeaf(BSTNode node) {
    if(node == null)
        return 0;

    int lcDepth = findDeepestLeaf(node.lc) + 1;
    int rcDepth = findDeepestLeaf(node.rc) + 1;
    
    return lcDepth > rcDepth ? lcDepth : rcDepth;
    }
    
    public void findLongestDistance() {
    int lcDepth = findDeepestLeaf(root.lc);
    int rcDepth = findDeepestLeaf(root.rc);
    System.out.println("Longest distance: " + (lcDepth + rcDepth));
    }

    public static void main(String[] args) {
    FindLongestDistanceInBinaryTree instance = new FindLongestDistanceInBinaryTree();
    instance.insert(10).insert(6).insert(12).insert(14);
    instance.insert(5).insert(4).insert(11).insert(9);
    instance.insert(7).insert(8).insert(15).insert(16);;
    instance.check();

    instance.findLongestDistance();
    }

    private FindLongestDistanceInBinaryTree insert(int value) {
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
}
