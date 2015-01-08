package careercup.amazon;

/*
 * http://www.careercup.com/question?id=13575664
 */
public class SwapKthElements {
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) {
        Node head = init(10);
        swap(head, 3);
        
        Node node = head;
        while(node != null) {
            System.out.print(node.value + "\t");
            node = node.next;
        }
    }
    
    // Suppose the list is long enough
    public static void swap(Node head, int i) {
        Node prev1 = null, prev2 = null;
        Node node = head, prev = null;
        int step = 1;
        while(step < i && node != null) {
            prev = node;
            node = node.next;
            step++;
        }
        
        if(step < i) {
            System.out.println("List is of less size");
            return;
        }

        prev1 = prev;
        Node node1 = node;
        
        // reset prev to keep it "i" steps from node
        prev2 = prev = head;
        while(node.next != null) {
            prev2 = prev;
            prev = prev.next;
            node = node.next;
        }
        Node node2 = prev2.next;
        
        Node tmp = node2.next;
        prev1.next = node2;
        node2.next = node1.next;
        
        prev2.next = node1;
        node1.next = tmp;
    }

    public static Node init(int N) {
        Node head = new Node(1);
        Node tail = head;
        for(int i = 2; i <= N; i++) {
            tail.next = new Node(i);
            tail = tail.next;
        }
        return head;
    }
}
