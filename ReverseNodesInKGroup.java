package lc;

public class ReverseNodesInKGroup {
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode node = head;
		ListNode tail = null;

		while (node != null) {
			ListNode newHead = node;
			ListNode newTail = node;

			int n = k;
			while (node != null && n > 0) {
				n--;
				node = node.next;
			}

			if (n > 0)
				break;

			// [currentHead, nextSection)
			ListNode toBeAdded = newHead.next;
			while (toBeAdded != node) {
				ListNode next = toBeAdded.next;
				toBeAdded.next = newHead;
				newHead = toBeAdded;
				toBeAdded = next;
			}

			if (tail == null) {
				head = newHead;
			} else {
				tail.next = newHead;
			}
			tail = newTail;
			newTail.next = node;
		}

		return head;
	}
	
	// my latest implementation
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode newHead = null, newTail = null;
        while(true) {
            int n = k;
            ListNode tail = head;
            while(--n > 0 && tail != null)
                tail = tail.next;
            
            if(tail == null) {
                if(newTail == null)
                    return head;
                else
                    newTail.next = head;
                break;
            }
            
            ListNode next = tail.next;
            tail.next = null;
            
            ListNode[] nodes = reverse(head);
            if(newHead == null)
                newHead = nodes[0];
            else
                newTail.next = nodes[0];
            newTail = nodes[1];
            
            head = next;
        }
        
        return newHead;
    }
    
    private ListNode[] reverse(ListNode head) {
        ListNode[] nodes = new ListNode[2];
        
        ListNode newHead = null, tail = null;
        while(head != null) {
            ListNode temp = head.next;
            
            if(tail == null)
                tail = head;
            
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        nodes[0] = newHead;
        nodes[1] = tail;
        return nodes;
    }
}