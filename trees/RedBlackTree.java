package trees;

import java.awt.Color;

import org.omg.CORBA.REBIND;

/*
 * http://zh.wikipedia.org/w/index.php?title=%E7%BA%A2%E9%BB%91%E6%A0%91&variant=zh-hans
 * http://blog.csdn.net/wuliming_sc/article/details/2949251
 * 
 * Attributes of RB tree:
 * 1) The color of a node is either red or black
 * 2) The root node is black
 * 3) All leaf nodes (NIL) are black
 * 4) The child nodes of a red node are all black (no consecutive red nodes in a path)
 * 5) In any path from a node to a leaf node, the numbers of black nodes are the same
 * 
 * From #4, the shortest path from root to a leaf contains only black nodes and the
 * longest path contains alternative red and black nodes.
 * From #5, no path is 2 times longer than the shortest path. Thus RB tree is "balanced"
 */

enum COLOR {
    RED, BLACK
}

class RBNode {
    /*
     * NIL only represents leaf node but not null reference!!!
     */
    public static final RBNode NIL = new RBNode(Integer.MIN_VALUE, COLOR.BLACK);

    COLOR color;
    int value;
    RBNode lc, rc;
    RBNode parent;

    public RBNode(int value) {
    this(value, COLOR.RED);
    }

    public RBNode(int value, COLOR color) {
    this.color = color;
    this.value = value;
    lc = rc = NIL;
    parent = null;
    }

    public String toString() {
    if (this == NIL)
        return "NIL ";

    StringBuilder sb = new StringBuilder();
    sb.append((char) value + " ");
    sb.append(color == COLOR.RED ? 'R' : 'B');

    sb.append(" (");
    if (lc != RBNode.NIL)
        sb.append(" " + lc.toString() + " ");
    else
        sb.append(" lc: NIL ");

    if (rc != RBNode.NIL)
        sb.append(" " + rc.toString() + " ");
    else
        sb.append(" rc: NIL ");
    sb.append(")");

    return sb.toString();
    }
}

public class RedBlackTree {
    RBNode root = null;

    public String toString() {
    if (root == null)
        return "null";
    else
        return root.toString();
    }

    /*
     * place the node in the right place suppose node has been initialized: 1)
     * color set to RED 2) lc and rc are set 3) parent is set 4) value is set
     */
    public void insert(RBNode node) {
    RBNode nodeToInsert = findNodeToInsert(node);
    if (nodeToInsert == null) {
        root = node;
        // See attribute #2
        node.color = COLOR.BLACK;
        return;
    }

    // don't insert duplicate value
    if (nodeToInsert.value == node.value) {
        return;
    } else if (nodeToInsert.value > node.value) {
        nodeToInsert.lc = node;
    } else {
        nodeToInsert.rc = node;
    }
    node.parent = nodeToInsert;

    postInsert(node);
    }

    /*
     * adjust colors, do rotations, etc.
     */
    private void postInsert(RBNode node) {
    if (node == root) {
        node.color = COLOR.BLACK;
        return;
    }

    /*
     * #2 attribute #4 and #5 stand
     */
    RBNode parent = parent(node);
    if (parent != null && parent.color == COLOR.BLACK)
        return;

    /*
     * below the parent node is RED and has parent. Otherwise it is the root
     * node, which is BLACK. So this node that will be inserted has an uncle
     * node, although which may be leaf node (NIL).
     */

    RBNode uncle = uncle(node);
    /*
     * #3 here we need to change the colors of parent and uncle to BLACK.
     * otherwise attribute #4 cannot be retained. Also, we need to change
     * the color of grandfather to RED (originally it must be BLACK).
     * however, the parent node of the grandfather may be RED. Thus we need
     * to apply the routine recursively
     */
    if (uncle != null && uncle.color == COLOR.RED) {
        parent.color = uncle.color = COLOR.BLACK;
        RBNode grand = grand(node);
        /*
         * since we have uncle, grand is not null
         */
        grand.color = COLOR.RED;

        /*
         * since now the grand is RED and may violate attribute #4, we need
         * to call insert(grand) recursively.
         */
        postInsert(grand);

        return;
    }

    /*
     * #4 uncle is null or BLACK
     */
    RBNode grand = grand(node);
    /*
     * grand is null means there is no uncle
     */
    if (grand == null)
        return;

    if (node == parent.rc && parent == grand.lc) {
        node.parent = grand;
        grand.lc = node;
        parent.rc = node.rc;
        node.lc = parent;
        parent.parent = node;

        /*
         * due to the above operations, we have to exchange the roles of
         * parent node and the current node
         */
        RBNode n = parent;
        parent = node;
        node = n;
    } else if (node == parent.lc && parent == grand.rc) {
        node.parent = grand;
        grand.rc = node;
        parent.lc = node.lc;
        node.rc = parent;
        parent.parent = node;

        RBNode n = parent;
        parent = node;
        node = n;
    }

    /*
     * #5 same as above situation, uncle is null or BLACK. otherwise we have
     * dealt with that in situation #3.
     * we need to get the father of the grand before it changes in "if" clause
     */
    RBNode fatherOfGrand = parent(grand);
    if (node == parent.lc && parent == grand.lc) {
        grand.lc = parent.rc;
        parent.rc = grand;
    } else if (node == parent.rc && parent == grand.rc) {
        grand.rc = parent.lc;
        parent.lc = grand;
    }
    if (fatherOfGrand != null) {
        if (grand == fatherOfGrand.lc)
        fatherOfGrand.lc = parent;
        else
        fatherOfGrand.rc = parent;
        parent.parent = fatherOfGrand;
    }

    grand.parent = parent;
    parent.color = COLOR.BLACK;
    grand.color = COLOR.RED;

    if (root == grand) {
        root = parent;
        /*
         * we have to remove the original parent pointer if a node becomes
         * root. otherwise there will be unexpected problem when calling
         * parent(root)
         */
        root.parent = null;
    }
    }

    /*
     * find the place to insert the node
     */
    private RBNode findNodeToInsert(RBNode node) {
    RBNode n = root;
    while (n != RBNode.NIL && n != null) {
        if (n.value == node.value) {
        break;
        }

        if (n.value > node.value) {
        if (n.lc == RBNode.NIL)
            return n;
        n = n.lc;
        } else {
        if (n.rc == RBNode.NIL)
            return n;
        n = n.rc;
        }
    }

    return n;
    }

    private RBNode parent(RBNode node) {
    return node.parent;
    }

    private RBNode uncle(RBNode node) {
    RBNode parent = parent(node);
    RBNode grand = grand(node);
    if (grand != null) {
        if (parent == grand.lc)
        return grand.rc;
        else
        return grand.lc;
    }

    return null;
    }

    private RBNode grand(RBNode node) {
    RBNode parent = node.parent;
    if (parent != null)
        return parent.parent;
    return null;
    }

    public void delete(int value) {
    RBNode node = findNodeToDelete(value);
    if (node == null || node == RBNode.NIL)
        return;

    if(node.lc != RBNode.NIL && node.rc != RBNode.NIL) {
        
    }
    
    if(node.color == COLOR.RED) {
        
    }
    
    /*
     * 
     */
    }

    /*
     * find the place to insert the node
     */
    private RBNode findNodeToDelete(int value) {
    RBNode n = root;
    while (n != null) {
        if (n.value == value) {
        return n;
        }

        if (n.value > value)
        n = n.lc;
        else
        n = n.rc;
    }

    return n;
    }

    public static void main(String[] args) {
    RedBlackTree tree = new RedBlackTree();
    for (int i = 0; i < 26; i++) {
        tree.insert(new RBNode('A' + i));
        tree.insert(new RBNode('a' + i));
    }
    System.out.println(tree);
    }
}