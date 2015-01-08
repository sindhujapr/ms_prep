public class FlattenList {
    public static void main(String[] args) {
        flatten();
        flatten_iter();
    }

    private static class ListNode {
        ListNode next, child;
        int val;
        public ListNode(int val) {this.val = val;}
    }

    private static ListNode init() {
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        for(int i = 1; i <= 10; i++) {
            map.put(i, new ListNode(i));
        }

        ListNode head = map.get(1);
        map.get(1).next = map.get(2);
        map.get(2).next = map.get(3);
        map.get(3).next = map.get(7);
        map.get(7).next = map.get(8);

        map.get(3).child = map.get(4);
        map.get(4).next = map.get(5);
        map.get(5).next = map.get(6);

        map.get(5).child = map.get(9);
        map.get(9).next = map.get(10);
        return head;
    }

    public static void flatten_iter() {
        System.out.println("\niter");

        ListNode head = init();
        ListNode node = head;
        while(node != null) {
            System.out.print(node.val + "(" + (node.child == null ? null : node.child.val) + ")\t");
            node = node.next;
        }
        System.out.println();

        node = head;
        while(node != null) {
            if(node.child == null) {
                node = node.next;
                continue;
            }

            ListNode next = node.next;
            ListNode child = node.child;
            ListNode temp = child;
            while(temp.next != null)
                temp = temp.next;

            temp.next = next;
            node.next = child;
            node.child = null;

            node = child;
        }

        node = head;
        while(node != null) {
            System.out.print(node.val + "\t");
            node = node.next;
        }
    }
    public static void flatten() {
        ListNode head = init();
        ListNode node = head;
        while(node != null) {
            System.out.print(node.val + "(" + (node.child == null ? null : node.child.val) + ")\t");
            node = node.next;
        }

        System.out.println();
        _flatten(head);

        node = head;
        while(node != null) {
            System.out.print(node.val + "\t");
            node = node.next;
        }
    }

    private static ListNode _flatten(ListNode head) {
        if(head == null)
            return head;

        ListNode tail = _flatten(head.child);
        if(tail != null) {
            tail.next = head.next;
            head.next = head.child;
            head.child = null;

            ListNode res = _flatten(tail.next);
            return res == null ? tail : res;
        } else {
            ListNode res = _flatten(head.next);
            return res == null ? head : res;
        }
    }

    public static int dist(int[] A) {
        int max1 = A[0];
        int max = 2 * A[0];
        for(int i = 1; i < A.length; i++) {
            max = Math.max(Math.max(max, 2*A[i]), A[i]+i+max1);

            max1 = Math.max(max1, A[i]-i);
        }
        return max;
    }
}
