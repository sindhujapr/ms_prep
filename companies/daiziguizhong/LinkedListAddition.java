package interview.daiziguizhong;

import java.util.Random;

/*
 * http://chuansongme.com/n/92111
 * http://hawstein.com/posts/add-singly-linked-list.html
 */
public class LinkedListAddition {
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) { this.val = val; }
        @Override public String toString() {
            StringBuilder builder = new StringBuilder();
            ListNode node = this;
            while(node != null) {
                builder.append(node.val).append("->");
                node = node.next;
            }
            return builder.toString();
        }
    }
    
    /*
     * return node1 as result without O(1) space.
     * node1 and nodes have equal number of elements but the code can be easily
     * changed to work for non-equal linked lists.
     */
    public ListNode add(ListNode node1, ListNode node2) {
        if(node1 == null || node2 == null)
            return null;
        
        ListNode p1 = node1, p2 = node2;
        ListNode head = node1, q = (node1.val+node2.val >= 9) ? null : node1;
        while(p1 != null) {
            int val = p1.val+p2.val;
            
            // keep q pointing to the highest bit that is not 9
            if(val < 9)
                q = p1;
            
            if(val >= 10) {
                p1.val = val % 10;
                
                // we need to create one node to store the carrier
                if(q == null) {
                    q = head = new ListNode(1);
                    head.next = node1;
                } else {
                    q.val += 1;
                }
                
                ListNode prev = q;
                q = q.next;
                while(q != p1) {
                    q.val = 0;
                    prev = q;
                    q = q.next;
                }
                // p1.val may equal to 9 and thus we move q back to prev
                if(p1.val == 9)
                    q = prev;
            } else {
                p1.val = val;
            }
            
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return head;
    }
    
    public void print(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
    
    public ListNode init(int val) {
        ListNode head = null;
        while(val > 0) {
            ListNode node = new ListNode(val % 10);
            val /= 10;
            node.next = head;
            head = node;
        }
        return head;
    }
    
    public static int convert(ListNode node) {
        int res = 0;
        while(node != null) {
            res = 10*res + node.val;
            node = node.next;
        }
        return res;
    }
    
    public static boolean compare(int v1, int v2) {
        while(v1 > 0 || v2 > 0) {
            if((v1 > 0 && v2 == 0) || (v1 == 0 && v2 > 0))
                return false;
            
            v1 /= 10;
            v2 /= 10;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        LinkedListAddition instance = new LinkedListAddition();
        Random rand = new Random(10000);
        for(int i = 0; i < 10000; i++) {
            int val1 = rand.nextInt(2000000), val2 = rand.nextInt(2000000);
            if(!compare(val1, val2))
                continue;
        
            ListNode node1 = instance.init(val1);
            ListNode node2 = instance.init(val2);
            int v1 = convert(instance.add(node1, node2));
            int v2 = val1+val2;
            if(v1 != v2)
                System.out.println(val1 + "\t" + val2);
        }
    }
}