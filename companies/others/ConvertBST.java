package test;

/**
 * Created by qingcunz on 9/25/14.
 */
public class ConvertBST {
      static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }

      static class ListNode {
             int val;
             ListNode next;
             ListNode(int x) { val = x; next = null; }
         }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        head.next.next= new ListNode(8);

        TreeNode root = new ConvertBST().sortedListToBST(head);
    }

    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            len++;
        }

        return convert(head, len);
    }

    private TreeNode convert(ListNode head, int len) {
        if(len <= 0)
            return null;

        ListNode node = head;
        int i = 0;
        while(i++ < len/2)
            node = node.next;

        TreeNode root = new TreeNode(node.val);
        root.left = convert(head, i-1);
        root.right = convert(node.next, len-i-2);
        return root;
    }
}
