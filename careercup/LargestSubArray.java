package careercup;

/*
 * http://blog.csdn.net/v_JULY_v/article/details/6444021
 * Get the largest sum of sub array
 */
public class LargestSubArray {
    public static int maxSum1(int[] A, int n) {
    int maximum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
        for (int k = i; k <= j; k++) {
            sum += A[k];
        }
        if (sum > maximum)
            maximum = sum;

        sum = 0;
        }
    }
    return maximum;
    }

    public static int maxSum2(int[] a, int n) {
    int sum = 0;
    int b = 0;

    for (int i = 0; i < n; i++) {
        /*
         * if the sum of all previous elements is less than 0, then discard
         * them
         */
        if (b < 0) {
        b = a[i];
        System.out.println("discard a[0, ..., " + (i - 1) + "]");
        } else {
        b += a[i];
        // System.out.println("add " + a[i]);
        }

        if (sum < b)
        sum = b;
    }
    return sum;
    }

    /*
     * take negatives into consideration
     */
    public static int maxSum3(int a[], int n) {
    int max = a[0];
    int sum = 0;

    for (int j = 0; j < n; j++) {
        if (sum >= 0)
        sum += a[j];
        else
        sum = a[j];

        /*
         * if a[j] is negative, max remains unless somewhere sum is greater
         * than max, eg, the following elements are -2, 3, ...
         */
        if (sum > max)
        max = sum;
    }
    return max;
    }

    /*
     * dynamic programming (incomplete)
     */
    public static int maxSum4(int a[], int n) {
    /*
     * for each element, we have the below decision: 1) if it's
     * non-negative, take it 2) if it's negative but the sum will not, take
     * it 3) if it's negative and the sum will also be, discard previous
     * result
     */
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum = max4(sum, a[i]);
    }

    // sum[i+1] = max(a[i+1], sum[i] + a[i+1]);
    // result = max(result, sum[i]);

    return sum;
    }

    public static int max4(int sum, int element) {
    if (sum >= 0)
        return sum + element;
    else {
        if (element > 0)
        return sum + element;
        else
        return sum;
    }
    }

    public static int MaxSubsequenceSum2(int A[], int N) {
    int ThisSum = 0, MaxSum = 0, i, j;
    for (i = 0; i < N; i++) {
        ThisSum = 0;
        for (j = i; j < N; j++) {
        ThisSum += A[j];
        if (ThisSum > MaxSum)
            MaxSum = ThisSum;
        }
    }
    return MaxSum;
    }

    public static void main(String[] args) {
    int[] array = { 1, -2, 3, 10, -4, 7, 2, -5 };
    System.out.println(maxSum1(array, array.length));
    System.out.println(maxSum2(array, array.length));
    System.out.println(maxSum3(array, array.length));
    // System.out.println(maxSum4(array, array.length));
    System.out.println(MaxSubsequenceSum2(array, array.length));
    }
}