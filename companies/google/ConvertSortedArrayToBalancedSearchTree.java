package interview.google;

import java.util.Arrays;

class Node {
    int value;
    Node lc, rc;
    Node parent;

    public Node(int value) {
	this.value = value;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(value);
	sb.append(" (");
	if (lc != null)
	    sb.append(lc.value);
	else
	    sb.append("null");
	sb.append(", ");

	if (rc != null)
	    sb.append(rc.value);
	else
	    sb.append("null");
	sb.append(")");
	return sb.toString();
    }
}

public class ConvertSortedArrayToBalancedSearchTree {
    public static void convertBST(int[] array) {
	int pivot = array.length / 2;

	Node root = new Node(array[pivot]);
	convert(array, 0, pivot-1, root);
	convert(array, pivot+1, array.length-1, root);
    }

    /*
     * construct a sub-tree for the sub-array
     */
    private static void convert(int[] array, int start, int end, Node node) {
	if (start > end)
	    return;

	int pivot = (end + start) / 2;
	Node newNode = new Node(array[pivot]);
	convert(array, start, pivot-1, newNode);
	convert(array, pivot+1, end, newNode);

	if (newNode.value < node.value)
	    node.lc = newNode;
	else
	    node.rc = newNode;
    }

    public static void main(String[] args) {
	int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16 };
	System.out.println(Arrays.toString(array));
	convertBST(array);
    }
}