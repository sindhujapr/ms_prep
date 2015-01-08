package geeksforgeeks.divide.conquer;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) { this.label = x; }
    }
    
    /*
     * http://fisherlei.blogspot.com/2013/11/leetcode-copy-list-with-random-pointer.html
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode node = head;
        while(node != null) {
            RandomListNode temp = new RandomListNode(node.label);
            temp.next = node.next;
            node.next = temp;
            node = temp.next;
        }
        
        node = head;
        while(node != null) {
            if(node.random != null) {
                RandomListNode temp = node.next;
                temp.random = node.random.next;
            }
            
            node = node.next.next;
        }
        
        RandomListNode newHead = null, newTail = null;
        node = head;
        while(node != null) {
            if(newHead == null) {
                newHead = newTail = node.next;
            } else {
                newTail.next = node.next;
                newTail = newTail.next;
            }
            
            node.next = node.next.next;
            node = node.next;
        }
        return newHead;
    }

    // additional space is used
    public RandomListNode copyRandomList1(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode newHead = null, newTail = null, node = head;
        while (node != null) {
            if (newTail == null) {
                newHead = newTail = new RandomListNode(node.label);
            } else {
                newTail.next = new RandomListNode(node.label);
                newTail = newTail.next;
            }

            map.put(node, newTail);
            node = node.next;
        }

        node = head;
        newTail = newHead;
        while (node != null && newTail != null) {
            if (node.random != null) {
                newTail.random = map.get(node.random);
            }

            node = node.next;
            newTail = newTail.next;
        }
        return newHead;
    }
}
