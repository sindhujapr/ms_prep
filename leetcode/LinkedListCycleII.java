package lc;

public class LinkedListCycleII {
    /*
     * idea here:
     * http://www.cnblogs.com/TenosDoIt/p/3416702.html
     * http://umairsaeed.com/2011/06/23/finding-the-start-of-a-loop-in-a-circular-linked-list/
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
            
        ListNode fast = head, slow = head;
        do {
            slow = slow.next;
            if(fast.next == null)
                return null;
            
            fast = fast.next.next;
            
            if(slow == fast)
                break;
        } while(slow != null && fast != null);
        
        if(slow != fast)
            return null;

        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
    // idea from http://blog.csdn.net/whuwangyi/article/details/14103993
    public ListNode detectCycle2(ListNode head) {
        if(head == null)
            return null;
            
        ListNode fast = head, slow = head;
        do {
            slow = slow.next;
            if(fast.next == null)
                return null;
            
            fast = fast.next.next;
            
            if(slow == fast)
                break;
        } while(slow != null && fast != null);
        
        if(slow != fast)
            return null;
        
        int len = 0;
        do {
            slow = slow.next;
            len++;
        } while(slow != fast);
        
        slow = fast = head;
        while(len-- > 0)
            fast = fast.next;
        
        while(true) {
            if(slow == fast)
                break;
                
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
