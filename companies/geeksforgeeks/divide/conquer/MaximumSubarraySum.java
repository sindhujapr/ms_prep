package geeksforgeeks.divide.conquer;


public class MaximumSubarraySum {
    public static void main(String[] args) {
        int array[] =  {-2, -5, 6, -2, -3, 1, 5, -6};
        System.out.println(maxSum(array));
        
        System.out.println(maxSum(array, 0, array.length-1));
    }
    
    public static int maxSum(int[] array) {
        assert array != null && array.length > 0;
        
        int maxSum = array[0];
        int sum = array[0];
        for(int i = 1; i < array.length; i++) {
            sum = Math.max(array[i], sum+array[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
    
    /*
     * Divide and Conquer:
     * http://www.geeksforgeeks.org/divide-and-conquer-maximum-sum-subarray/
     * 
     * The above code uses DP:
     * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     */
    public static int maxSum(int[] array, int start, int end) {
        if(start == end)
            return array[start];
        
        int mid = (start+end) >>> 1;
        int left = maxSum(array, start, mid);
        int right = maxSum(array, mid+1, end);
        
        int maxl = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = mid; i >= start; i--) {
            sum += array[i];
            maxl = Math.max(maxl, sum);
        }
        
        int maxr = Integer.MIN_VALUE;
        sum = 0;
        for(int i = mid+1; i <= end; i++) {
            sum += array[i];
            maxr = Math.max(maxr, sum);
        }
        
        return Math.max(Math.max(left, right), maxr+maxl);
    }
}