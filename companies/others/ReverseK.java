package test;

/**
 * Created by qingcunz on 11/30/14.
 */
public class ReverseK {
    public static void main(String[] args) {
        ReverseK ins = new ReverseK();
        ListNode res = ins.reverseKGroup(ins.init(), 2);
        while(res != null) {
            System.out.print(res.val  + " ");
            res = res.next;
        }
    }

    private ListNode init() {
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        return res;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 && head == null)
            return head;

        ListNode newHead = null, newTail = null;

        while(head != null) {
            // section to be reversed
            ListNode sHead = head;
            int cnt = 0;
            while(++cnt < k && head != null)
                head = head.next;

            // we have reached the end of the list
            if(head == null) {
                if(newHead == null) {
                    newHead = sHead;
                } else {
                    newTail.next = sHead;
                }
                break;
            }

            ListNode temp = head.next;
            head.next = null;
            reverse(sHead);

            if(newHead == null) {
                newHead = sHead;
                newTail = head;
            } else {
                newTail.next = head;
                newTail = head;
            }

            head = temp;
        }

        return newHead;
    }

    private ListNode reverse(ListNode head) {
        if(head.next == null)
            return head;

        ListNode temp = head.next;
        head.next = null;
        ListNode newHead = reverse(temp);
        temp.next = head;
        return newHead;
    }

    private static class ListNode {
             int val;
             ListNode next;
             ListNode(int x) {
                 val = x;
                 next = null;
             }
         }
}
