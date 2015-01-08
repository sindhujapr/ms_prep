package leetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode (int x) { val = x; next = null; }
}

public class ConvertSorteListToBST {
    public ListNode init(int[] array) {
    ListNode head = null;
    ListNode tail = head;
    
    for(int value : array) {
        if(head == null) {
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
     * construct the BST recursively exactly the same way, it's strange
     * that judge large will fail.
     */
    public TreeNode sortedListToBST(ListNode head) {
        int length = 0;
        ListNode node = head;
        while(node != null) {
            length++;
            node = node.next;
        }
        
        return convert(head, length);
    }
    
    private TreeNode convert(ListNode head, int length) {
        if(length <= 0)
            return null;
        
        int mid = length/2;
        ListNode node = head;
        int i = 0;
        while(i++ < mid) {
            node = node.next;
        }
        
        TreeNode root = new TreeNode(node.val);
        
        /*
         *  pay attention to the length! mid is actually half the length,
         *  if it is used as index, there are <mid> elements before it.
         */        
        TreeNode left = convert(head, mid);
        TreeNode right = convert(node.next, length-mid-1);
        
        if(left != null)
            root.left = left;
        if(right != null)
            root.right = right;

        return root;
    }
    
    public static void main(String[] args) {
    int[] array = {1, 3};
    
    ConvertSorteListToBST instance = new ConvertSorteListToBST();
    ListNode head = instance.init(array);
    instance.sortedListToBST(head);
    }
}