package lc;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode sorted = null, unsorted = head;
        while(unsorted != null) {
            ListNode temp = unsorted.next;
            unsorted.next = null;
            
            ListNode prev = null, node = sorted;
            while(node != null && node.val < unsorted.val) {
                prev = node;
                node = node.next;
            }
            
            if(prev == null) {
                unsorted.next = sorted;
                sorted = unsorted;
            } else {
                prev.next = unsorted;
                unsorted.next = node;
            }
            
            unsorted = temp;
        }
        
        return sorted;
    }
}