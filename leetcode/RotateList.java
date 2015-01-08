package lc;

public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode n1 = head, n2 = head;
        while (n > 0) {
            n2 = n2.next;
            n--;
            if (n2 == null)
                n2 = head;
        }

        // avoid cycle
        if (n1 == n2)
            return n1;

        while (n2.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }

        ListNode temp = n1.next;
        n2.next = head;
        n1.next = null;
        return temp;
    }
}