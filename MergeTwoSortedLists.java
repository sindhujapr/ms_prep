package lc;

public class MergeTwoSortedLists {
	public static void main(String[] args) {
		MergeTwoSortedLists instance = new MergeTwoSortedLists();
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(1);
		ListNode head = instance.mergeTwoLists(node1, node2);
		instance.printList(head);
	}
	
	public void printList(ListNode head) {
		while(head != null) {
			System.out.print(head.val + "\t");
			head = head.next;
		}
	}

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        while(l1 != null || l2 != null) {
            ListNode node = null;
            if(l1 != null && l2 != null) {
                node = l1.val > l2.val ? l2 : l1;
            } else if(l1 == null && l2 != null) {
                node = l2;
            } else {
                node = l1;
            }
            
            if(node == l2)
                l2 = l2.next;
            else
                l1 = l1.next;
            
            if(head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        
        return head;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        
        ListNode head = null, tail = null;
        while(l1 != null || l2 != null) {
            if((l1 == null && l2 != null) || (l1 != null && l2 == null)) {
                ListNode node = l1 != null ? l1 : l2;
                
                if(head == null) {
                    head = node;
                    break;
                } else {
                    tail.next = node;
                    break;
                }
            } else if(l1 != null && l2 != null) {
                ListNode node = l1.val < l2.val ? l1 : l2;
                if(head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
                
                if(l1.val < l2.val)
                    l1 = l1.next;
                else
                    l2 = l2.next;
            }
        }
        
        return head;
    }

    // why it doesn't work?
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        while(l1 != null || l2 != null) {
            int v1 = (l1 == null ? Integer.MAX_VALUE : l1.val);
            int v2 = (l2 == null ? Integer.MAX_VALUE : l2.val);
            
            if(v1 <= v2) {
                if(head == null) {
                    head = tail = l1;
                } else {
                    tail.next = l1;
                    tail = tail.next;
                }
                
                l1 = l1.next;
            } else {
                if(head == null) {
                    head = tail = l2;
                } else {
                    tail.next = l2;
                    tail = tail.next;
                }
                l2 = l2.next;
            }
        }
        
        return head;
    }
}
