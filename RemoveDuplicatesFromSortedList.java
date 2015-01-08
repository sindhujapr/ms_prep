package lc;

public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode tail = head;
        while(tail != null) {
            ListNode node = tail.next;
            while(node != null && node.val == tail.val)
                node = node.next;
                    
            tail.next = node;
            tail = node;
        }

        return head;
    }
}
