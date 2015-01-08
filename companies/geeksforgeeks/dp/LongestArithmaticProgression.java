package geeksforgeeks.dp;

/*
 * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
 */
public class LongestArithmaticProgression {
    public static void main(String[] args) {
        int array1[] = {5, 10, 15, 20, 25, 30};
        int array2[] = {1, 7, 10, 15, 27, 29};
        int array3[] = {5, 10, 13, 15, 16, 17};
        System.out.println(longest(array1) + "\t" + lenghtOfLongestAP(array1));
        System.out.println(longest(array2) + "\t" + lenghtOfLongestAP(array2));
        System.out.println(longest(array3) + "\t" + lenghtOfLongestAP(array3));
    }
    
    /*
     * wrong implementation
     */
    public static int longest(int array[]) {
        assert array.length > 2;
        
        int n = array.length;
        int[] matrix = new int[n];
        
        for(int i = 1; i < n-1; i++) {
            int k = i-1;
            int l = i+1;
            
            while(k >= 0 && l < n) {
                int sum = array[k] + array[l];
                if(sum == 2 * array[i]) {
                    if(matrix[i] == 0)
                        matrix[l] = 3;
                    else
                        matrix[l] = matrix[i] + 1; 
                    break;
                } else if(sum > 2 * array[i]) {
                    k--;
                } else {
                    l++;
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = n-1; i > 1; i--)
            max = Math.max(max, matrix[i]);
        return max;
    }
    
    public static int lenghtOfLongestAP(int set[]) {
        int n = set.length;
        if (n <= 2)  return n;
     
        int L[][]  = new int[n][n];
        int llap = 2;  // Initialize the result
     
        for (int i = 0; i < n; i++)
            L[i][n-1] = 2;
     
        // Consider every element as second element of AP
        for (int j=n-2; j>=1; j--) {
            int i = j-1, k = j+1;
            while (i >= 0 && k <= n-1) {
               if (set[i] + set[k] < 2*set[j]) {
                   k++;
               } else if (set[i] + set[k] > 2*set[j]) {
                   L[i][j] = 2;
                   i--;
               } else {
                   L[i][j] = L[j][k] + 1;
                   llap = Math.max(llap, L[i][j]);
     
                   i--; k++;
               }
            }
     
            while (i >= 0) {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }
}