package interview.ms;

import java.util.ArrayList;
import java.util.List;

/*
 * search from the root node down to any leaf node and output
 * all paths that the sum of the elements equals to a given number
 * for example, we output the two paths {10, 12} and {10, 5, 7}
 *    10
 *    / \
 *   5  12
 *  / \
 * 4   7
 */
public class SearchInBSTForGivenSum {
    private static BSTNode root = null;

    public static void main(String[] args) {
	SearchInBSTForGivenSum instance = new SearchInBSTForGivenSum();
	instance.insert(10).insert(5).insert(12).insert(4).insert(7);
	instance.check();

	instance.findPaths(root, 22);
	System.out.println();
    }

    List<BSTNode> nodes = new ArrayList<BSTNode>();

    public void findPaths(BSTNode node, int weight) {
	nodes.add(node);
	weight -= node.value;

	/*
	 * make sure this is leaf node and the corresponding path qualifies
	 */
	if (node.lc == null && node.rc == null && weight == 0) {
	    for (BSTNode n : nodes) {
		System.out.print(n.value + " ");
	    }
	    System.out.println();
	} else {
	    if (node.lc != null) {
		findPaths(node.lc, weight);
		nodes.remove(nodes.size() - 1);
	    }

	    if (node.rc != null) {
		findPaths(node.rc, weight);
		nodes.remove(nodes.size() - 1);
	    }
	}
    }

    private SearchInBSTForGivenSum insert(int value) {
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
