package interview.ms;

public class ListCrossed {
    public static SingleListNode buildList(int[] array) {
	SingleListNode head = null;
	SingleListNode tail = null;
	for (int i = 0; i < array.length; i++) {
	    if(head == null) {
		tail = new SingleListNode(array[i]);
		head = tail;
	    } else {
		tail.next = new SingleListNode(array[i]);
		tail = tail.next;
	    }
	}
	
	return head;
    }

    public static boolean ListsCrossed(SingleListNode h1, SingleListNode h2) {
	for(SingleListNode n1 = h1; n1 != null; n1 = n1.next) {
	    for(SingleListNode n2 = h2; n2 != null; n2 = n2.next) {
		if(n1.value == n2.value)
		    return true;
	    }
	}
	
	return false;
    }
    
    public static void printList(SingleListNode head) {
	System.out.println();
	SingleListNode node = head;
	while (node != null) {
	    System.out.print(node.value);
	    System.out.print(" ");
	    node = node.next;
	}
    }
    
    public static SingleListNode reverseList(SingleListNode head) {
	SingleListNode newHead = null;
	while(head != null) {
	    SingleListNode next = head.next;
	    head.next = newHead;
	    newHead = head;
	    head = next;
	}
	
	return newHead;
    }
    
    public static SingleListNode reverseList1(SingleListNode newHead, SingleListNode head) {
	if(head == null)
	    return newHead;
	
	SingleListNode next = head.next;
	head.next = newHead;
	newHead = head;
	head = next;
	
	return reverseList1(newHead, head);
    }
    
    public static void main(String[] args) {
	int[] array1 = { 3, 2 ,4, 8, 7, 6};
	int[] array2 = { 9, 1 ,12, 10, 11, 5};
	
	SingleListNode h1 = buildList(array1);
	SingleListNode h2 = buildList(array2);
	System.out.println(ListsCrossed(h1, h2));
	
	printList(h1);
	printList(reverseList(h1));
	
	printList(h2);
	printList(reverseList1(null, h2));
    }
}
