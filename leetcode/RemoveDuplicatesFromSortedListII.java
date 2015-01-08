package lc;

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null)
            return head;

        ListNode node = head;
        ListNode prev = null;
        while (node != null) {
            int value = node.val;
            ListNode tmp = node;
            node = node.next;
            if (node != null && node.val == value) {
                while (node != null && node.val == value)
                    node = node.next;

                if (prev == null)
                    head = node;
                else
                    prev.next = node;
            } else {
                prev = tmp;
            }
        }

        return head;
    }
    
    // seems easier to understand and implement
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = null, newTail = null;
        while(head != null) {
            int val = head.val;
            ListNode temp = head;
            head = head.next;
            while(head != null && head.val == val)
                head = head.next;
            
            // check if head has moved one more step
            if(head == temp.next) {
                if(newTail == null) {
                    newHead = newTail = temp;
                } else {
                    newTail.next = temp;
                    newTail = temp;
                }
                
                // MUST!!!
                newTail.next = null;
            }
        }
        return newHead;
    }        
}