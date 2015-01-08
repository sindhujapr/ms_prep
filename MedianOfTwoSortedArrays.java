package lc2;

import java.util.ArrayList;
import java.util.List;

/*
 * http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
 * http://blog.csdn.net/beiyetengqing/article/details/8555592
 * http://www.youtube.com/watch?v=_H50Ir-Tves
 * http://blog.csdn.net/a83610312/article/details/11850233
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/
 * http://www.geeksforgeeks.org/median-of-two-sorted-arrays/ (both arrays have same number of elements)
 * 
 * Analysis from http://www.geeksforgeeks.org/median-of-two-sorted-arrays/ for a specific problem:
 * 
 * The basic idea is that if you are given two arrays ar1[] and ar2[] and know the length of each,
 * you can check whether an element ar1[i] is the median in constant time. Suppose that the median 
 * is ar1[i]. Since the array is sorted, ar1[i] is greater than exactly <i> values in array ar1[]. 
 * Then if it is the median, it is also greater than exactly j = n-i-1 elements in ar2[].
 * It requires constant time to check if ar2[j] <= ar1[i] <= ar2[j + 1]. If ar1[i] is not the median,
 * then depending on whether ar1[i] is greater or less than ar2[j] and ar2[j + 1], you know that 
 * ar1[i] is either greater than or less than the median.
 * Thus you can binary search for median in O(lg n) worst-case time.
 */

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] array1 = { 1, 3, 4, 7, 8, 10 };
        int[] array2 = { 2, 5, 6, 9 };

        MedianOfTwoSortedArrays instance = new MedianOfTwoSortedArrays();
        System.out.println(instance.findKthPosition(array1, array2, 2));
        System.out.println(instance.findKthPosition(array2, array1, 1));
        System.out.println(instance.findMedianSortedArrays(array1, array2));
//      System.out.println(instance.findMedianSortedArrays(array1, array2));
//
//      for (int i = 1; i <= array1.length + array2.length; i++)
//          System.out.println(instance.findKthSmallest(array1, array2, i));
//
//      Solution.main(null);
    }
    
    // http://gongxuns.blogspot.com/2012/12/leetcodemedian-of-two-sorted-arrays.html
    public double findMedianSortedArrays2(int A[], int B[]) {
        int m = A.length, n = B.length;

        if (m == 0)
            return n % 2 == 1 ? B[n / 2] : (double) (B[n / 2 - 1] + B[n / 2]) / 2.;
        if (n == 0)
            return m % 2 == 1 ? A[m / 2] : (double) (A[m / 2 - 1] + A[m / 2]) / 2.;

        int temp = findKthPosition(A, B, (m + n) / 2 + 1);
        if(temp >= 0) {
            temp = A[temp];
        } else {
            temp = findKthPosition(B, A, (m + n) / 2 + 1);
            temp = B[temp];
        }
        
        int temp1 = temp;
        if ((m + n) % 2 == 0) {
            temp1 = findKthPosition(A, B, (m + n) / 2);
            if (temp1 < 0) {
                temp1 = findKthPosition(B, A, (m + n) / 2);
                temp1 = B[temp1];
            } else {
                temp1 = A[temp1];
            }
        }
        return (double) (temp + temp1) / 2.;
    }

    // k starts from 1 not 0!
    private int findKthPosition(int[] a, int[] b, int k) {
        int low = 0, high = a.length-1;
        while(low <= high) {
            int cur = (low+high) >> 1;
            int i1 = k-cur-2, i2 = k-cur-1;
            if(i1 < b.length && i2 >= 0 && (i1 < 0 || a[cur] >= b[i1]) && (i2 >= b.length || a[cur] <= b[i2]))
                return cur;
            else if(i2 <= 0 || (i2 < b.length && a[cur] > b[i2]))
                high = cur-1;
            else
                low = cur+1;
        }
        
        return -1;
    }
    
    public double findMedianSortedArrays(int A[], int B[]) {
        int size = A.length + B.length;
        if (size % 2 == 0) {
            return (findKthSmallest(A, B, size / 2) + findKthSmallest(A, B, size / 2 + 1)) / 2.0;
        } else {
            return (double) findKthSmallest(A, B, (size + 1) / 2);
        }
    }

    /*
     * k starts from 1 and thus the corresponding index is (k-1)
     */
    public int findKthSmallest(int A[], int B[], int k) {
        int pa = Math.min(A.length, k - 1);
        int delta = (pa + 1) / 2;
        return findKthSmallest(A, B, pa, delta, k);
    }

    public int findKthSmallest(int[] A, int[] B, int pa, int delta, int k) {
        // invariant: pa+pb = k-1
        int pb = (k - 1) - pa;
        System.out.format("pa=%d\tpb=%d\tdelta=%d\n", pa, pb, delta);
        int Ai_1 = ((pa == 0) ? Integer.MIN_VALUE : A[pa - 1]);
        int Bj_1 = ((pb == 0) ? Integer.MIN_VALUE : B[pb - 1]);
        int Ai = ((pa == A.length) ? Integer.MAX_VALUE : A[pa]);
        int Bj = ((pb == B.length) ? Integer.MAX_VALUE : B[pb]);

        // B[j-1] <= A[i] <= B[j]
        if (Bj_1 <= Ai && Ai <= Bj)
            return Ai;
        if (Ai_1 <= Bj && Bj <= Ai)
            return Bj;

        if (Ai > Bj) {
            pa = pa - delta;
            if (((k - 1) - pa) > B.length) {
                pa = k - 1 - B.length;
            }
        } else {
            pa = pa + delta;
            if (pa > Math.min(A.length, k - 1)) {
                pa = Math.min(A.length, k - 1);
            }
        }
        return findKthSmallest(A, B, pa, (delta + 1) / 2, k);
    }
        
    public double findMedianSortedArrays3(int A[], int B[]) {
        if (A.length == 0)
            return getMedian(B);
        if (B.length == 0)
            return getMedian(A);

        return getMedian(A, 0, A.length - 1, B, 0, B.length - 1);
    }

    private double getMedian(int[] array) {
        if (array.length % 2 == 0)
            return (array[array.length / 2 - 1] + array[array.length / 2]) / 2.0;
        else
            return array[array.length / 2];
    }

    private double getMedian(int A[], int la, int ra, int B[], int lb, int rb) {
        if (la + 1 >= ra || lb + 1 >= rb) {
            List<Integer> stack = new ArrayList<Integer>();
            while (la <= ra && lb <= rb) {
                if (A[la] <= B[lb])
                    stack.add(A[la++]);
                else
                    stack.add(B[lb++]);
            }
            while (la <= ra)
                stack.add(A[la++]);
            while (lb <= rb)
                stack.add(B[lb++]);

            int size = stack.size();
            if (size % 2 == 1)
                return stack.get(size / 2);
            else
                return (stack.get(size / 2 - 1) + stack.get(size / 2)) / 2.0;
        }

        int ma = (la + ra) / 2;
        int mb = (lb + rb) / 2;
        boolean odda = ((ra - la + 1) % 2) == 1;
        boolean oddb = ((rb - lb + 1) % 2) == 1;
        int min_rm, rma, rmb;
        if (A[ma] >= B[mb]) {
            if (odda && oddb) {
                rma = ra - ma;
                rmb = mb - lb;
            } else if (!odda && !oddb) {
                rma = ra - ma - 1;
                rmb = mb - lb + 1;
            } else {
                rma = ra - ma;
                rmb = mb - lb + 1;
            }
            min_rm = rma > rmb ? rmb : rma;
            return getMedian(A, la, ra - min_rm, B, lb + min_rm, rb);
        } else {
            if (odda && oddb) {
                rmb = rb - mb;
                rma = ma - la;
            } else if (!odda && !oddb) {
                rmb = rb - mb - 1;
                rma = ma - la + 1;
            } else {
                rmb = rb - mb;
                rma = ma - la + 1;
            }
            min_rm = rma > rmb ? rmb : rma;
            return getMedian(A, la + min_rm, ra, B, lb, rb - min_rm);
        }
    }
}
