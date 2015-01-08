package interview.skt;

class Node {
	int id; // Unique value
	Node[] children;
	
	public Node(int id) { this.id = id; }
}
	
public class TreeAlgorithm {
	private static Node root = new Node(0);
	private static int SIZE = 100;
	/* global variable to indicate the number of nodes in the tree. */
	static int nodeCnt = 0;

	//Size needs to be big enough for hold all connections
	static int array1[] = new int[SIZE];
	static int array2[] = new int[SIZE];
	
	/* do whatever initialization */
	public static void init(Node root) {
		root.children = new Node[10];
		int id = 1;

		for(int i = 0; i < 10; i++) {
			root.children[i] = new Node(id++);
			// each child has two child nodes
			root.children[i].children = new Node[2];
		}
		
		// add children nodes for each child node of root
		for(int j = 0; j < root.children.length; j++) {
			root.children[j].children = new Node[2];
			Node node1 = root.children[j].children[0] = new Node(id++);
			Node node2 = root.children[j].children[1] = new Node(id++);
			
			node1.children = new Node[1];
			node1.children[0] = new Node(id++);
			node2.children = new Node[1];
			node2.children[0] = new Node(id++);
		}
	}
	
	/* for each pair (id1, id2) from array1 and array2, we check if there is another pair that starts with id2 in array1*/
	private static int calculate1(Node root) {
		scan(root);

		int count = 0;
		for (int i = 0; i < nodeCnt; i++) {
			/* here we have one pair (id1, id2) from two arrays */
			int id1 = array1[i];
			int id2 = array2[i];
			
			/* match another pair in array1 that starts from (id2) */
			for(int j = i; j < nodeCnt; j++) {
				int id = array1[j];
				if (id == id2) {
					System.out.println("found (" + id1 + ", " + id2 + ", " + array2[j] + ")");
					count++;
				}
			}
		}
		
		return count;
	}
	
	/* non-recursion */
	private static int calculate2(Node node) {
		if(node == null || node.children == null)
			return 0;
		
		int count = 0;

		for(Node child : node.children) {
			if(child != null && child.children != null) {
				for(int i = 0; i < child.children.length; i++) {
					Node cc = child.children[i];
					System.out.println("found (" + node.id + ", " + child.id + ", " + cc.id + ")");
					count++;
				}
			}
			count += calculate2(child);
		}
		
		return count;
	}
	
	/* Traverse the tree to find out all connections. If there is a connection between node1 (id1) and
	 * node2(id2), then id1 and id2 will be kept in array1 and array2, respectively.
	 */	
	private static void scan(Node node) {
		if(node == null || node.children == null)
			return;

		for(Node child : node.children) {
			array1[nodeCnt] = node.id;
			array2[nodeCnt] = child.id;
			nodeCnt++;
			assert nodeCnt < SIZE;
			// Recursively add all children nodes to the array
			scan(child);
		}
	}
	
	public static void main(String[] args) {
		init(root);
		System.out.println(calculate1(root));
		System.out.println("-----------------------another algorightm--------------------------------");
		System.out.println(calculate2(root));
	}
}