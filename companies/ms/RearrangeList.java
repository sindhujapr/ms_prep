package interview.ms;

class SingleListNode {
    int value;
    SingleListNode next;

    public SingleListNode(int value) {
	this.value = value;
    }

    public String toString() {
	if (RearrangeList.isChar(value))
	    return new Character((char) value).toString();
	else
	    return Integer.toString(value);
    }
}

public class RearrangeList {
    private static SingleListNode head = null;

    public static void buildSingleList(int[] array) {
	SingleListNode tail = null;
	for (int i = 0; i < array.length; i++) {
	    if (tail == null) {
		head = tail = new SingleListNode(array[i]);
	    } else {
		tail.next = new SingleListNode(array[i]);
		tail = tail.next;
	    }
	}
    }

    public static void rearrangeArray() {
	SingleListNode intNode, charNode;

	SingleListNode tail, newHead;
	tail = newHead = null;

	while (true) {
	    charNode = searchNextCharAndRemove();
	    if (charNode == null)
		break;

	    /*
	     * suppose char comes first in the new list. so we need to take care
	     * of null reference here, but not when dealing with int node
	     */
	    if (tail == null) {
		tail = charNode;
		newHead = charNode;
	    } else {
		tail.next = charNode;
		tail = tail.next;
	    }

	    intNode = searchNextIntAndRemove();
	    if (intNode == null)
		break;

	    tail.next = intNode;
	    tail = tail.next;
	}

	// deal with the left part, which may be all integers or all chars
	intNode = searchNextIntAndRemove();
	if (intNode != null) {
	    tail.next = intNode;
	}

	charNode = searchNextCharAndRemove();
	if (charNode != null) {
	    tail.next = charNode;
	}

	head = newHead;
    }

    /*
     * since it's singly linked list, it's not easy to remove the returned node
     * because we have to locate the previous node in another loop. Instead, we
     * locate the node and remove it in one loop, then return it to the caller,
     * who appends it to the newly created list.
     */
    private static SingleListNode searchNextIntAndRemove() {
	SingleListNode node, prev;
	node = prev = null;
	for (node = head; node != null; node = node.next) {
	    if (!isChar(node.value)) {
		if (prev == null) {
		    head = head.next;
		} else {
		    prev.next = node.next;
		}
		return node;
	    }
	    prev = node;
	}

	return null;
    }

    private static SingleListNode searchNextCharAndRemove() {
	SingleListNode node, prev;
	node = prev = null;
	for (node = head; node != null; node = node.next) {
	    if (isChar(node.value)) {
		if (prev == null) {
		    head = head.next;
		} else {
		    prev.next = node.next;
		}
		return node;
	    }
	    prev = node;
	}

	return null;
    }

    public static boolean isChar(int ch) {
	if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
	    return true;
	else
	    return false;
    }

    public static void printList(SingleListNode head) {
	System.out.println();
	SingleListNode node = head;
	while (node != null) {
	    if (isChar(node.value))
		System.out.print((char) node.value);
	    else
		System.out.print(node.value);
	    System.out.print(" ");
	    node = node.next;
	}
    }

    public static void main(String[] args) {
	int[] array = { 'A', 'B', 1, 'C', 2, 3, 'D', 'E', 4, 5, 6, 7, 'F', 'G',
		'H', 8, 9 };
	buildSingleList(array);
	printList(head);
	rearrangeArray();
	printList(head);
    }
}