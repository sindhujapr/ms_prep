package interview.java;

public class MyList {
	private static class Node {
		public Node(int value) {
			this.value = value;
			next = null;
		}
		private int value;
		Node next;
	}
	
	private Node head;
	private Node current;
	
	public MyList() {
		head = current = null;
	}

	public void add(int value) {
		Node n = new Node(value);
		n.next = null;

		if(head == null) {
			head = current = n;
		} else {
			current.next = n;
			current = n;
		}
	}

	public int remove(int index) {
		assert index >= 0;

		Node prev = null;
		Node node = head;
		for(int i = 0; node != null; prev = node, node = node.next, i++) {
			if(i == index)
				break;
		}
		
		prev.next = node.next;
		return node.value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Node node = head; node != null; node = node.next) {
			sb.append(node.value + ", ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MyList list = new MyList();
		for(int i = 0; i < 100; i++)
			list.add(i);
		System.out.println(list);
		
		for(int i = 2; i < 4; i++) {
			System.out.println(list.remove(i));
			System.out.println(list);
		}
		System.out.println(list);
	}
}
