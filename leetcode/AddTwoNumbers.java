package lc;

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers instance = new AddTwoNumbers();
        ListNode result = instance.addTwoNumbers(new ListNode(5), new ListNode(5));
        instance.printList(result);
    }
    
    public void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
    
    /*
     * see better implementation here:
     * http://gongxuns.blogspot.com/2012/12/leetcodeadd-two-numbers.html
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carrier = 0;
        while(l1 != null || l2 != null || carrier != 0) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            
            ListNode node = new ListNode((v1+v2+carrier)%10);
            carrier = (v1+v2+carrier)/10;
            if(head == null)
                head = node;
            else
                tail.next = node;
            tail = node;
            
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        
        return head;
    }
}