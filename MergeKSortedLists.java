package lc;

import java.util.ArrayList;

/*
 * O(nlgk) (n is the count of numbers) using heap
 * http://www.geeksforgeeks.org/merge-k-sorted-arrays/
 */
public class MergeKSortedLists {
    // divide and conquer
    public ListNode mergeKLists(List<ListNode> lists) {
        return merge(lists, 0, lists.size()-1);
    }
    
    private ListNode merge(List<ListNode> lists, int start, int end) {
        if(start > end)
            return null;

        if(start == end)
            return lists.get(start);
        
        int mid = (start+end) >> 1;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid+1, end);

        ListNode head = null, tail = null;
        
        while(left != null || right != null) {
            ListNode node = null;
            if(left == null && right != null) {
                node = right;
            } else if(left != null && right == null) {
                node = left;
            } else {
                node = left.val < right.val ? left : right;
            }
            
            if(node == left)
                left = left.next;
            else
                right = right.next;
                
            if(head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        
        return head;
    }

    // as of sep/2014, this doesn't pass large judge
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        ListNode head = null, tail = null;
        while(true) {
            ListNode minNode = null;
            int minIndex = -1;
            for(int i = 0; i < lists.size(); i++) {
                ListNode node = lists.get(i);
                if(node != null && (minNode == null || node.val < minNode.val)) {
                    minNode = node;
                    minIndex = i;
                }
            }
            
            if(minNode == null)
                break;
            
            lists.remove(minIndex);
            lists.add(minIndex, minNode.next);
            minNode.next = null;
            if(head == null)
                head = minNode;
            else
                tail.next = minNode;

            tail = minNode;
        }
        
        return head;
    }
}
