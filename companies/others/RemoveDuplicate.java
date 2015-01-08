package test;

/**
 * Created by qingcunz on 10/22/14.
 */
public class RemoveDuplicate {
    private static class ListNode {
        ListNode next;
        int val;
        public ListNode(int val) { this.val = val; }
        @Override public String toString() { return Integer.toString(val); }
    }

    public static void main(String[] args) {
        ListNode head = new RemoveDuplicate().deleteDuplicates(init());
        while(head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }

    public static ListNode init() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        return head;
    }

    // wrong implementation
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = null, tail = null;
        while(head != null) {
            if(newHead == null) {
                newHead = tail = head;
            } else {
                tail.next = head;
                tail = head;
            }
            tail.next = null;

            head = head.next;
            while(head != null && head.val == tail.val)
                head = head.next;
        }
        return newHead;
    }
}
