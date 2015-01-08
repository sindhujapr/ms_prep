package lc;

public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        if(A == null || A.length == 0)
            return 0;
            
        int maxSoFar = A[0], max = A[0];
        for(int i = 1; i < A.length; i++) {
            maxSoFar = Math.max(maxSoFar+ A[i], A[i]);
            max = Math.max(max, maxSoFar);
        }
        
        return max;
    }

    public int maxSubArray(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            if (sum > max) {
                max = sum;
            }

            if (sum < 0)
                sum = 0;
        }

        return max;
    }

    public static int max(int[] array) {
        assert array != null && array.length > 0;
        
        int max = array[0];
        int sum = array[0];
        int start = 0;
        int end = 0;

        for(int i = 1; i < array.length; i++) {
            if(array[i] > sum + array[i]) {
                sum = array[i];
                start = i;
            } else {
                sum += array[i];
            }
            
            if(sum > max) {
                max = sum;
                end = i;
            }
        }

        System.out.println(start + "\t" + end);
        return max;
    }
    
    /*
     * divide and conquer
     */
    public int maxSubArray1(int[] A) {
        return max(A, 0, A.length - 1, Integer.MIN_VALUE);
    }

    private int max(int[] A, int low, int high, int maxValue) {
        if (low > high)
            return maxValue;

        int mid = (low + high) >>> 1;
        int maxl = max(A, low, mid - 1, maxValue);
        int maxr = max(A, mid + 1, high, maxValue);

        maxValue = Math.max(maxl, maxValue);
        maxValue = Math.max(maxr, maxValue);

        int sum = 0;
        maxl = Integer.MIN_VALUE;
        for (int i = mid - 1; i >= low; i--) {
            sum += A[i];
            if (sum > maxl)
                maxl = sum;
        }

        sum = 0;
        maxr = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > maxr)
                maxr = sum;
        }

        return Math.max(maxValue, maxl + maxr + A[mid]);
    }
}
