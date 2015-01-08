package geeksforgeeks.divide.conquer;

/*
 * code is from the below link but seems incorrect. See leetcode.MedianOfTwoSortedArrays.java
 * for the correct implementation.
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
 */
public class MedianOfDiffSizeSortedArray {
    public static void main(String[] args) {
        int[] A1 = {1, 3, 5, 7};
        int[] B1 = {2, 6, 9, 12, 15, 20};
        System.out.println(findMedian(A1, B1));
        
        int A2[] = {1};
        int B2[] = {5, 8, 10, 20};
        System.out.println(findMedian(B2, A2));
        
        int A3[] = {1, 3};
        int B3[] = {2, 4};
        System.out.println(findMedian(A3, B3));
        
        int A4[] = {1, 3};
        int B4[] = {2, 4, 6};
        System.out.println(findMedian(A4, B4));
        
        int A5[] = {1, 2, 2};
        int B5[] = {1, 2, 3};
        System.out.println(findMedian(A5, B5));

        // the code doesn't yield correct result for the below case
        int A6[] = {1, 2};
        int B6[] = {3, 4, 5};
        System.out.println(findMedian(A6, B6));
    }
    
    public static double findMedian(int[] A, int[] B) {
        if(A.length > B.length)
            return findMedian(B, 0, B.length-1, A, 0, A.length-1);
        else
            return findMedian(A, 0, A.length-1, B, 0, B.length-1);
    }
    
    // Suppose ae-as < be-bs, which means B has more elements
    private static double findMedian(int A[], int as, int ae, int B[], int bs, int be) {
        if(as > ae) {
            if((be-bs+1) %2 == 1)
                return (double) B[(bs+be)/2];
            else
                return (B[(bs+be)/2] + B[(bs+be)/2+1]) / 2.0;
        } else if(bs > be) {
            if((ae-as+1) %2 == 1)
                return (double) A[(as+ae)/2];
            else
                return (A[(as+ae)/2] + A[(as+ae)/2+1]) / 2.0;
        }

        if(ae == as) {      // Only one element in A
            if(bs == be) {
                return (A[as] + B[bs]) / (double) 2;
            } else if(be == bs+1) {
                return Math.max(B[bs], Math.min(A[ae], B[be]));
            } else if((be-bs+1) % 2 == 1) { // number of elements in B is odd
                int mid = (be+bs) >> 1;
                int min = Math.min(Math.min(A[as], B[mid]), Math.min(B[mid-1], B[mid+1]));
                int max = Math.max(Math.max(A[as], B[mid]), Math.max(B[mid-1], B[mid+1]));
                
                return (A[as] + B[mid] + B[mid-1] + B[mid+1] - min - max) / (double) 2;
            } else {    // even
                int mid = (be+bs) >> 1;
                return Math.max(B[mid], Math.min(A[as], B[mid+1]));
            }
        } else if(ae == as+1) { // Only two elements in A
            if(be == bs+1) {
                int min = Math.min(Math.min(A[as], A[ae]), Math.min(B[bs], B[be]));
                int max = Math.max(Math.max(A[as], A[ae]), Math.max(B[bs], B[be]));

                return (A[as] + A[ae] + B[bs] + B[be] - min - max) / (double) 2;
            } else if((be-bs+1) % 2 == 1) { // odd
                int mid = (bs+be) >> 1;
                return Math.max(A[ae], Math.min(B[mid], A[as]));
            } else {    // even
                int mid = (bs+be) >> 1;
                int min = Math.min(Math.min(A[as], A[ae]), Math.min(B[mid], B[mid+1]));
                int max = Math.max(Math.max(A[as], A[ae]), Math.max(B[mid], B[mid+1]));

                return (A[as] + A[ae] + B[mid] + B[mid+1] - min - max) / (double) 2;
            }
        }
        
        int ma = (as+ae) >> 1;
        int mb = (bs+be) >> 1;
        // We need to drop the same number of elements from A and B
        if(A[ma] > B[mb])   // ignore A[ma+1, ... ae]: ae-(ma+1)+1 = ae-ma
            return findMedian(A, as, ma, B, bs+(ae-ma), be);
        else                // ignore A[as, ...., ma]: ma-as+1
            return findMedian(A, ma, ae, B, bs, be-(ma-as));
    }
}