package lc;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
     @Override public String toString() { return Integer.toString(val); }
 }

public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node = head;
        for(int i = 2; i < 4; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        
        ReverseLinkedListII instance = new ReverseLinkedListII();
        ListNode newHead = instance.reverseBetween(head, 2, 3);
        node = newHead;
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || m == n)
            return head;

        ListNode tail = null;
        ListNode node = head;
        int cnt = 1;
        while(cnt++ < m) {
            tail = node;
            node = node.next;
        }
        
        ListNode newHead = node;
        ListNode newTail = node;
        node = node.next;
        while(cnt++ <= n) {
            ListNode tmp = node;
            node = node.next;
            tmp.next = newHead;
            newHead = tmp;
        }

        newTail.next = node;
        if(tail != null) {
            tail.next = newHead;
            return head;
        } else {
            return newHead;
        }
    }
}