package careercup.fb;

import java.util.Arrays;

/*
 * http://www.careercup.com/question?id=23594662
 */
public class MaxSumSubarrayNoCont {
    public static void main(String[] args) {
        int[] array = {1, -1, 3, 8, 4};
        System.out.println(maxSum(array));
    }
    
    public static int maxSum(int[] array) {
        assert array != null && array.length > 1;
        
        int maxSum[] = new int[array.length];
        maxSum[0] = array[0];
        for(int i = 1; i < array.length; i++) {
            /*
             * choose among four:
             * a[i], sum[i-1], sum[i-2], sum[i-2]+a[i] 
             */
            maxSum[i] = Math.max(array[i], maxSum[i-1]);
            
            if(i >= 2) {
                maxSum[i] = Math.max(maxSum[i], array[i] + maxSum[i-2]);
                maxSum[i] = Math.max(maxSum[i-2], maxSum[i]);
            }
        }
        
        System.out.println(Arrays.toString(maxSum));
        return maxSum[array.length-1];
    }
}
