package geeksforgeeks.dp;

/*
 * this is strictly increasing subsequence.
 * For LIS problem, see
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] array = {-2, 1, -1, 2, -2, 3, 3, 5, 4, -1, 0, 1, 2};
        System.out.println(longestSequence(array));
    }
    
    /*
     * another implementation:
     * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
     * http://en.wikipedia.org/wiki/Patience_sorting
     */
    public static int longestSequence(int[] array) {
        int[] length = new int[array.length];
    
        /*
         * We can optimize the inner loop by starting from the end element:
         * the goal is to find an element smaller than the current one.
         * If there is such an element, just extend the sequence. Otherwise
         * make the current element the first element of a new sequence.
         */
        for(int i = 0; i < length.length; i++) {
            length[i] = 1;
            for(int j = 0; j < i; j++)
                if(array[i] == array[j]+1)
                    length[i] = length[j]+1;
        }
        
        return length[length.length-1];
    }
}