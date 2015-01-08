package lc;

/*
 * http://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEnd {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
            
        ListNode node1 = head, node2 = head, prev = null;
        while(n-- > 0)
            node1 = node1.next;
        
		// if we want to remove the first node
        if(node1 == null)
            return head.next;
        
        while(node1.next != null) {
            node2 = node2.next;
            node1 = node1.next;
        }

		// here we're sure the node we'll remove is not the head
        node2.next = node2.next.next;
        return head;       
    }
}
