package dynamic.programming;

import java.util.Arrays;

/*
 * Check CLR for details of this algorithm
 */
public class AssemblyLine {
    private final static int NODECNT = 6;
    /* cost for entrance */
    private static int e[] = {2, 4};
    /* cost for exit */
    private static int x[] = {3, 2};

    /* cost of each node on line 1 */
    private static int S1[] = {7, 9, 3, 4, 8, 4};
    /* cost of each node on line 2 */
    private static int S2[] = {8, 5, 6, 4, 5, 7};
    /* cost from node[i] on line 1 to node[i+1] on line 2 */
    private static int t1[] = {2, 3, 1, 3, 4};
    /* cost from node[i] on line 2 to node[i+1] on line 1 */
    private static int t2[] = {2, 1, 2, 2, 1};

    private static int min(int val1, int val2) {
        return val1 > val2 ? val2 : val1;
    }
    
    public static int findShortest() {
        /* shortest path for subproblem */
        int f1[] = new int[NODECNT];
        int f2[] = new int[NODECNT];
        f1[0] = e[0] + S1[0];
        f2[0] = e[1] + S2[0];
        int l1[] = new int[NODECNT-1];
        int l2[] = new int[NODECNT-1];
        
        for (int i = 1; i < NODECNT; i++) {
            if(f1[i-1] < f2[i-1] + t2[i-1]) {
                f1[i] = f1[i-1] + S1[i];
                l1[i-1] = 1;    //shortest through node i on line 1
            } else {
                f1[i] = f2[i-1] + t2[i-1] + S1[i];
                l1[i-1] = 2;    //shortest through node i on line 1
            }
            
            if(f2[i-1] < f1[i-1] + t1[i-1]) {
                f2[i] = f2[i-1] + S2[i];
                l2[i-1] = 2;
            } else {
                f2[i] = f1[i-1] + t1[i-1] + S2[i];
                l2[i-1] = 1;
            }
        }
        
        System.out.println(Arrays.toString(f1));
        System.out.println(Arrays.toString(f2));
        
        System.out.println(Arrays.toString(l1));
        System.out.println(Arrays.toString(l2));
        return min(f1[f1.length-1]+x[0], f2[f2.length-1]+x[1]);
    }
    
    public static void main(String[] args) {
        System.out.println("shortest line " + findShortest());
    }
}