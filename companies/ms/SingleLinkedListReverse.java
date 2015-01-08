package interview.ms;

import java.util.Iterator;

class Node<T> {
    private T value;
    private Node<T> next;
    
    Node(T value) {
        this.value = value;
        this.next = null;
    }
    
    public void setNext(Node<T> next) { this.next = next; }
    public Node<T> getNext() { return next; }
    public T getValue() { return value; }
    public String toString() { return value.toString(); }
}

// Reverse a linked list. Avoid using recursion
public class SingleLinkedListReverse {
    private static int length = 10;
    
    public static void main(String[] args) {
        Node<Integer> head = new Node<Integer>(1);
        Node<Integer> current = head;
        
        for (int i = 2; i <= length; i++) {
            Node<Integer> node = new Node<Integer>(i);
            current.setNext(node);
            current = node;
        }

        current = head;
        while(current != null) {
            System.out.print(current + ", ");
            current = current.getNext();
        }
        System.out.println();
        
        Node<Integer> newHead = reverseList1(head);
        current = newHead;
        while(current != null) {
            System.out.print(current + ", ");
            current = current.getNext();
        }
    }
    
    public static <T> Node<T> reverseList1(Node<T> node) {
        Node<T> current = node;
        Node<T> next = current.getNext();

        while(next != null) {
            Node<T> temp = next.getNext();

            next.setNext(current);
            current = next;
            next = temp;
        }
        
        node.setNext(null);
        return current;
    }
    
    public static <T> Node<T> reverseList2(Node<T> node) {
        Node<T> current = node;
        Node<T> next = current.getNext();

        while(next != null) {
            Node<T> temp = next.getNext();

            next.setNext(current);
            
            current = next;
            next = temp;
            
        }
        
        node.setNext(null);
        return current;
    }
    
    public static void test() {
        // Extreme cases: 0, null, negative, maximum, etc.
        // Input error: null or negative, etc.
        // General cases
    }
}