package lc;

public class PartitionList {
	// my latest code
    public ListNode partition(ListNode head, int x) {
        ListNode node = head;
        ListNode big_head = null, big_tail = null;
        ListNode small_head = null, small_tail = null;
        
        while(node != null) {
            ListNode temp = node.next;
            // must!!! test with {2, 1} and 2
            node.next = null;
            if(node.val < x) {
                if(small_head == null) {
                    small_head = small_tail = node;
                } else {
                    small_tail.next = node;
                    small_tail = node;
                }
            } else {
                if(big_head == null) {
                    big_head = big_tail = node;
                } else {
                    big_tail.next = node;
                    big_tail = node;
                }
            }
            
            node = temp;
        }
        
        if(small_tail != null)
            small_tail.next = big_head;
        
        return small_head == null ? big_head : small_head;
    }
    
    public ListNode partition1(ListNode head, int x) {
        ListNode big = head;
        ListNode big_prev = null;

        /*
         * find the occurrence of the first BIG value, and then insert all
         * SMALL values after it before it.
         */
        while(big != null && big.val < x) {
            big_prev = big;
            big = big.next;
        }
        
        if(big == null)
            return head;

        ListNode small_prev = big;
        ListNode small = big.next;
        while(true) {            
            while(small != null && small.val >= x) {
                small_prev = small;
                small = small.next;
            }
            
            if(small == null)
                break;
            
            ListNode tmp = small.next;
            
            small_prev.next = small.next;
            small.next = big;
            
            /*
             * special situation: the first BIG value is the head and thus
             * doesn't have a previous node.
             */
            if(big_prev != null) {
                big_prev.next = small;
            } else {
                head = small;
            }
            big_prev = small;
            
            small = tmp;
        }
        
        return head;
    }
}
