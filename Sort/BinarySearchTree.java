package Sort;

class Node {
    int val;
    Node parent, lc, rc;
    public Node(int value) { val = value; }
}

public class BinarySearchTree {
    private static String separator = "*";
    private static String blank = "_";

    public static int exp(int i) {
        if(i == 0)
            return 1;
            
        int val = 1;
        for(int j = 0; j < i; j++)
            val *= 2;
        return val;
    }

    public static void printTree(int arr[], int height) {
        int totalNum = exp(height) - 1;

        for(int h = 0; h < height; h++) {
            int sepNum = exp(height-h-1) -1;
            for(int i = 0; i < sepNum; i++)
                System.out.print(separator);
            
            /* Number of elements to be displayed for this level */
            int eleNum = exp(h);
            /* Number of blanks to be displayed between two elements */
            int blankNum = (eleNum == 1) ? 0 : (totalNum - 2 * sepNum - eleNum) / (eleNum - 1);
            /* Starting index for this line */
            int start = exp(h) - 1;

            for(int i = start; (i-start+1) <= eleNum && i < arr.length; i++){
                System.out.print(arr[i]);
                for(int j = 0; j < blankNum; j++)
                    System.out.print(blank);
            }
            System.out.println();
        }
    }
    
    public static int parent(int index) {
        return (index-1) / 2;
    }

    public static int left(int index) {
        return 2 * index + 1;
    }
    
    public static int right(int index) {
        return 2 * index + 2;
    }
    
    public static void treeInsert(int arr[], int p) {
        
    }
    
    public static Node init(int arr[]) {
        if(arr.length == 0)
            return null;

        Node root = new Node(arr[0]);
        root.parent = null;
        Node node1 = root.lc = new Node(arr[1]);
        Node node2 = root.rc = new Node(arr[2]);
        node1.parent = node2.parent = root;
        
        Node node3 = node1.lc = new Node(arr[3]);
        Node node4 = node1.rc = new Node(arr[4]);
        node3.parent = node4.parent = node1;

        Node node5 = node2.lc = new Node(arr[5]);
        Node node6 = node2.rc = new Node(arr[6]);
        node5.parent = node6.parent = node2;

        Node node7 = node3.lc = new Node(arr[7]);
        Node node8 = node3.rc = new Node(arr[8]);
        node7.parent = node8.parent = node3;

        Node node9 = node4.lc = new Node(arr[9]);
        Node node10 = node4.rc = new Node(arr[10]);
        node9.parent = node10.parent = node4;

        Node node11 = node5.lc = new Node(arr[11]);
        Node node12 = node5.rc = new Node(arr[12]);
        node11.parent = node12.parent = node5;

        Node node13 = node6.lc = new Node(arr[13]);
        Node node14 = node6.rc = new Node(arr[14]);
        node13.parent = node14.parent = node6;
        
        node7.lc = node7.rc = node8.lc = node8.rc = 
                node9.lc = node9.rc = 
                node10.lc = node10.rc = 
                node11.lc = node11.rc =
                node12.lc = node12.rc = 
                node13.lc = node13.rc =
                node14.lc = node14.rc = null;

        return root;
    }
    
    /**
     * @param node
     */
    public static void printl(Node node) {
        System.out.print("left children of node " + node.val + ": ");
        Node n = node;
        while(n.lc != null) {
            n = n.lc;
            System.out.print(n.val + " ");
        }
        System.out.println();
    }
    
    public static void printr(Node node) {
        System.out.print("right children of node " + node.val + ": ");
        Node n = node;
        while(n.rc != null) {
            n = n.rc;
            System.out.print(n.val + " ");
        }
        System.out.println();
    }
    
    public static void printBinarySearchTree(Node node) {
        Node n = node;

        System.out.print("current node: " + n.val + " ");
        if(n.lc != null)
            printl(n.lc);
            
        if(n.rc != null)
            printl(n.rc);
        System.out.println();
    }
    
    public static void main(String[] args) {
        int arr[] = {8, 4, 12, 2, 5, 10, 15, 1, 3, 4, 6, 11, 13, 14, 16};
        Node root = init(arr);
//      printTree(arr, 4);
        printBinarySearchTree(root);
    }
}