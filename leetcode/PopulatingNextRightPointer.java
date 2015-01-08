package lc;

class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
     public String toString() {
         return Integer.toString(val);
     }
 }

public class PopulatingNextRightPointer {
    // my own code 1
    public void connect1(TreeLinkNode root) {
        if(root == null)
            return;
        
        TreeLinkNode rightMost = root.left, leftMost = root.right;
        connect(rightMost);
        connect(leftMost);
        
        while(rightMost != null && leftMost != null) {
            rightMost.next = leftMost;
            
            rightMost = rightMost.right;
            leftMost = leftMost.left;
        }
    }
    
    /*
     * my own code 2:
     * each time we construct the current layer, we take advantage of the
     * layer that we constructed.
     */
    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;
        
        TreeLinkNode lastHead = root, curr = root.left;
        while(curr != null) {
            TreeLinkNode temp = curr;
            
            while(lastHead != null) {
                if(curr == lastHead.left) {
                    curr.next = lastHead.right;
                    curr = curr.next;
                } else {
                    lastHead = lastHead.next;
                    if(lastHead != null) {
                        curr.next = lastHead.left;
                        curr = curr.next;
                    }
                }
            }
            lastHead = temp;
            curr = temp.left;
        }
    }
    
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
        
        TreeLinkNode lastTail = root;
        while(lastTail.left != null) {
            TreeLinkNode head = lastTail.left;
            TreeLinkNode curTail = null;
            while(lastTail != null) {
                if(curTail == null) {
                    curTail = lastTail.left;
                } else {
                    curTail.next = lastTail.left;
                    curTail = curTail.next;
                }
                curTail.next = lastTail.right;
                curTail = curTail.next;
    
                lastTail = lastTail.next;
            }            
            
            lastTail = head;
        }
    }
}