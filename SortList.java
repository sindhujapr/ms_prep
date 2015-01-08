package lc;

/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		
		ListNode res = new SortList().sortList(head);
		while(res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}
	}
	
    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode node = head;
        while(node != null) {
            n++;
            node = node.next;
        }
        
        return sort(head, n);
    }
    
    private ListNode sort(ListNode head, int n) {
        if(n <= 1)
            return head;
        
        int cnt = 1;
        ListNode node = head;
        while(cnt++ < n/2)
            node = node.next;
        
        ListNode next = node.next;
        node.next = null;
        
        ListNode head1 = sort(head, n/2), head2 = sort(next, n-n/2);
        
        ListNode newHead = null, tail = null;
        while(head1 != null || head2 != null) {
            ListNode tmp = null;
            if((head1 == null && head2 != null) || (head1 != null && head2 != null && head1.val >= head2.val)) {
                tmp = head2;
                head2 = head2.next;
            } else if((head1 != null && head2 == null) || (head1 != null && head2 != null && head1.val < head2.val)) {
                tmp = head1;
                head1 = head1.next;
            }
            
            if(newHead == null)
                newHead = tail = tmp;
            else {
                tail.next = tmp;
                tail = tail.next;
            }
        }
        
        return newHead;
    }
}