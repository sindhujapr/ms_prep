package lc;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        
        ListNode node1 = head, node2 = head;
        do {
            node1 = node1.next;
            node2 = node2.next == null ? null : node2.next.next;
            if(node1 == node2 && node1 != null)
                return true;
        } while(node1 != null && node2 != null);
        
        return false;
    }
}
