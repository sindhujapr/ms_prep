package lc2;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ConvertSorteListToBST {
    public ListNode init(int[] array) {
        ListNode head = null;
        ListNode tail = head;

        for (int value : array) {
            if (head == null) {
                tail = head = new ListNode(value);
                continue;
            }

            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return head;
    }

    /*
     * if we copy the values from the linked list to an array, and then
     * construct the BST recursively exactly the same way, it's strange that
     * judge large will fail.
     */
    public TreeNode sortedListToBST(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        return convert(head, length);
    }

    private TreeNode convert(ListNode head, int length) {
        if (length <= 0)
            return null;

        ListNode mid = head;
        int cnt = 0;
        while (cnt < length / 2) {
            mid = mid.next;
            cnt++;
        }

        TreeNode root = new TreeNode(mid.val);
        root.left = convert(head, cnt);
        root.right = convert(mid.next, length - cnt - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] array = { 1, 3 };

        ConvertSorteListToBST instance = new ConvertSorteListToBST();
        ListNode head = instance.init(array);
        instance.sortedListToBST(head);
    }
}