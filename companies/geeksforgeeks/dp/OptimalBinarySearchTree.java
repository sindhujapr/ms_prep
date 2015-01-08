package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
 */
public class OptimalBinarySearchTree {
    public static void main(String[] args) {
        int keys[] = {10, 12, 20};
        int freq[] = {34, 8, 50};
        
        System.out.println(optimalBST(keys, freq));
    }
    
    public static int optimalBST(int[] keys, int[] freq) {
        int n = keys.length;
        int[][] temp = new int[n][n];
        
        for(int i = 0; i < n; i++)
            temp[i][i] = freq[i];
        
        for(int gap = 1; gap < n; gap++) {
            for(int i = 0; i + gap < n; i++) {
                int j = i+gap;
                int sum = 0;

                int min = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++) {
                    min = Math.min(min, (k == i ? 0 : temp[i][k-1]) + (k == j ? 0 : temp[k+1][j]));
                    sum += freq[k];
                }
                    
                temp[i][j] = sum + min;
            }
        }
        
        return temp[0][n-1];
    }
}
