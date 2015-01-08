package careercup.fb;

public class ReverseLinkedList {
    static class Node {
        int value;
        Node next;
        public Node(int v) {
            this.value = v;
        }
    }
    
    public static void main(String[] args) {
        Node head = new Node(1);
        Node tail = head;
        for(int i = 2; i <= 10; i++) {
            tail.next = new Node(i);
            tail = tail.next;
        }
        
        Node newHead = null;
        Node node = head;
        while(node != null) {
            Node tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }
        
        node = newHead;
        while(node != null) {
            System.out.print(node.value + ", ");
            node = node.next;
        }
    }
}
