package lc;

public class ReorderList {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;
            
        int n = 0;
        ListNode node = head;
        while(node != null) {
            n++;
            node = node.next;
        }
        
        int i = (n & 1) == 1 ? n >> 1 : (n-1) >> 1;
        node = head;
        while(i-- > 0)
            node = node.next;
        
        ListNode second = reverse(node.next);
        node.next = null;
        
        node = head;
        while(node != null && second != null) {
            ListNode temp = second.next;
            second.next = node.next;
            node.next = second;
            
            node = second.next;
            second = temp;
        }
    }
    
    private ListNode reverse(ListNode head) {
        if(head.next == null)
            return head;
        
        ListNode temp = head.next;
        head.next = null;
        ListNode newHead = reverse(temp);
        temp.next = head;
        return newHead;
    }
}