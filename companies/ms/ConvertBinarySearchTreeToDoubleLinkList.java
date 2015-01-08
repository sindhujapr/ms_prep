package interview.ms;

import org.omg.CORBA.INTERNAL;

import sun.org.mozilla.javascript.internal.Node;

/*
 * convert a binary search tree to a sorted double link list
 * requirements: don't create any node, just adjust the pointers
 * for example, the below tree will be converted to double link list
 * 4=6=8=10=12=14=16
 *    10
 *   /  \
 *  6    14
 * / \   / \
 *4   8 12 16
 */

public class ConvertBinarySearchTreeToDoubleLinkList {
    private BSTNode root = null;

    /*
     * find the next node that has greater value than the current one, which
     * means: 1) the parent node if this is a lc of the parent node 2) go up the
     * tree to find a node, which is not a rc. then return its parent
     */
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

    public BSTNode convert() {
	BSTNode head, tail;
	head = tail = findNext(null);
	BSTNode next = findNext(head);

	while (next != null) {
	    /*
	     * we have to keep the next node before making any change
	     */
	    BSTNode tmp = findNext(next);
	    tail.next = next;
	    next.prev = tail;
	    tail = next;

	    next = tmp;
	}

	return head;
    }

    private ConvertBinarySearchTreeToDoubleLinkList insert(int value) {
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
	ConvertBinarySearchTreeToDoubleLinkList instance = new ConvertBinarySearchTreeToDoubleLinkList();
	instance.init();
	instance.check();

	BSTNode head = instance.convert();
	while (head != null) {
	    System.out.print(head.value + " ");
	    if (head.next == null)
		break;
	    head = head.next;
	}
	System.out.println();
	while (head != null) {
	    System.out.print(head.value + " ");
	    head = head.prev;
	}
    }
}