package interview.skt;

public class ReverseLinkedList {
	static class Node {
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
			next = null;
		}
	}

	public static Node init() {
		Node head = new Node(0);
		Node current = head;
		
		for (int i = 1; i < 100; i++) {
			current.next = new Node(i);
			current = current.next;
		}

		return head;
	}
	
	public static Node reverse(Node head) {
		/* Initialize before reverting
		 * The list is separated into two list: one is in the original order
		 * the other is the reverted part 
		 */
		Node newHead = head;
		head = head.next;
		newHead.next = null;
		
		/*
		 * For each loop, we need to remove a node from the original list and add it
		 * to the reverted part, until no element is available in the former.
		 */
		while(head != null) {
			Node next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		
		return newHead;
	}
	
	public static void printList(Node head) {
		for(Node node = head; node != null; node = node.next)
			System.out.print(node.value + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node head = init();
		printList(head);
		Node newHead = reverse(head);
		System.out.println("=======================================");
		printList(newHead);
	}
}
