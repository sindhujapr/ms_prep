package lc;

public class SwapNodesInPairs {
	private class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode init(int limit) {
		ListNode head = null;
		ListNode tail = null;

		for (int i = 1; i <= limit; i++) {
			ListNode node = new ListNode(i);
			if (head == null) {
				head = tail = node;
				continue;
			}
			tail.next = node;
			tail = node;
		}
		return head;
	}

	public void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + "  ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SwapNodesInPairs instance = new SwapNodesInPairs();
		ListNode head = instance.init(5);
		instance.printList(head);
		head = instance.swapPairs(head);
		instance.printList(head);
	}

    public ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode tail = null;

        while(node != null) {
            ListNode nextNode = node.next;
            if(nextNode == null)
                break;
            
            ListNode tmp = nextNode.next;
            if(tail != null) {
                tail.next = nextNode;
            } else {
                head = nextNode;
            }
            
            nextNode.next = node;
            node.next = tmp;
            tail = node;
            
            node = tmp;
        }
        
        return head;
    }

	// my latest as of sep/2014
    public ListNode swapPairs(ListNode head) {
        ListNode newHead = null, newTail = null;
        if(head == null || head.next == null)
            return head;
        
        while(head != null && head.next != null) {
            ListNode temp = head.next.next;
            
            if(newHead == null) {
                newHead = newTail = head.next;
                newTail.next = head;
            } else {
                newTail.next = head.next;
                newTail.next.next = head;
            }
            newTail = head;
            newTail.next = temp;
                
            head = temp;
        }
        
        return newHead;
    }
}
