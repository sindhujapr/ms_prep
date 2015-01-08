package interview.ms;

class BSTNode {
    int value;
    BSTNode parent;
    BSTNode lc;
    BSTNode rc;

    /*
     * if we only use lc and rc to represent prev and next, respectively, there
     * will be problem when we traverse the tree.
     */
    BSTNode prev, next;

    public BSTNode(int value) {
	this.value = value;
    }

    public String toString() {
	return String.valueOf(value);
    }
}