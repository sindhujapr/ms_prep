package lc;

public class PopulatingNextRightPointerII {
	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
//		root.right = new TreeLinkNode(3);

//		new PopulatingNextRightPointerII().connect(root);
		new PopulatingNextRightPointerII().connect1(root);
	}

	public void connect1(TreeLinkNode root) {
        if(root == null)
            return;
        
        TreeLinkNode last = root;
        TreeLinkNode curr = root.left != null ? root.left : root.right;
        while(curr != null) {
            TreeLinkNode temp = curr;
            
            while(curr != null) {
                // find the next node
                TreeLinkNode next = null;
                if(curr == last.left) {
                    next = last.right;
                    last = last.next;
                } else {
                    last = last.next;
                }

                while(next == null && last != null) {
                    next = last.left != null ? last.left : last.right;
                    if(next == null)
                        last = last.next;
                }
                
                curr.next = next;
                curr = curr.next;
            }
            
            last = temp;
            curr = temp.left != null ? temp.left : temp.right;
            if(curr == null)
                temp = temp.next;
            while(curr == null && temp != null) {
                curr = temp.left != null ? temp.left : temp.right;
                if(curr == null)
                    temp = temp.next;
            }
        }
	}
	
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
        
        TreeLinkNode last = root;
        TreeLinkNode curTail = root.left == null ? root.right : root.left;
        while(curTail != null) {
            TreeLinkNode temp = curTail;
            
            while(last != null) {
                if(last.left != null && last.left != curTail) {
                    curTail.next = last.left;
                    curTail = curTail.next;
                }
                
                if(last.right != null && last.right != curTail) {
                    curTail.next = last.right;
                    curTail = curTail.next;
                }
                last = last.next;
            }
            
            last = temp;
            while(temp != null) {
                curTail = temp.left != null ? temp.left : temp.right;
                if(curTail != null)
                    break;
                temp = temp.next;
            }
        }
    }
}