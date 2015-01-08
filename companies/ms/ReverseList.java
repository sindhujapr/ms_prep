package interview.ms;

public class ReverseList {
    public static SingleListNode buildList(int[] array) {
        SingleListNode head = null;
        SingleListNode tail = null;
        for (int i = 0; i < array.length; i++) {
            if (head == null) {
                tail = new SingleListNode(array[i]);
                head = tail;
            } else {
                tail.next = new SingleListNode(array[i]);
                tail = tail.next;
            }
        }

        return head;
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

    public static SingleListNode reverseList1(SingleListNode head) {
        SingleListNode newHead = null;
        while (head != null) {
            SingleListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }

    /*
     * recurrsion
     */
    public static SingleListNode reverseList2(SingleListNode newHead, SingleListNode head) {
        if (head == null)
            return newHead;

        SingleListNode next = head.next;
        head.next = newHead;
        newHead = head;
        head = next;

        return reverseList2(newHead, head);
    }
    
    /*
     * http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
     */
    public static SingleListNode reverseList3(SingleListNode head) {
        if(head == null)
            return null;
        
        if(head.next == null)
            return head;
        
        SingleListNode tail = head.next;
        head.next = null;
        
        // The below sequence cannot be reversed
        SingleListNode newHead = reverseList3(tail);
        tail.next = head;
        
        return newHead;
    }

    public static void main(String[] args) {
        int[] array1 = { 3, 2, 4, 8, 7, 6 };
        int[] array2 = { 9, 1, 12, 10, 11, 5 };

        SingleListNode h1 = buildList(array1);
        SingleListNode h2 = buildList(array2);

        printList(h1);
        printList(reverseList1(h1));

//      printList(h2);
//      printList(reverseList2(null, h2));
        
        printList(h2);
        printList(reverseList3(h2));
    }
}