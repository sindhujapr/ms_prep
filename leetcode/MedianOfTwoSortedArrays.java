package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
 * http://www.youtube.com/watch?v=_H50Ir-Tves
 */
public class MedianOfTwoSortedArrays {
    /*
     * k starts from 1 and thus the corresponding index is (k-1)
     */
    public int findKthSmallest(int A[], int B[], int k) {
        int pa = Math.min(A.length, k - 1);
        int delta = (pa + 1) / 2;
        return findKthSmallest(A, B, pa, delta, k);
    }

    /*
     * inefficient
     */
    public int findKthSmallest(int[] A, int[] B, int pa, int delta, int k) {
        /*
         * invariant: pa+pb = k-1
         */
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

    public static void main(String[] args) {
        int[] array1 = { 1, 3, 4, 7, 8, 10 };
        int[] array2 = { 2, 5, 6, 9 };

        MedianOfTwoSortedArrays instance = new MedianOfTwoSortedArrays();
        System.out.println(instance.findMedianSortedArrays(array1, array2));

        for (int i = 1; i <= array1.length + array2.length; i++)
            System.out.println(instance.findKthSmallest(array1, array2, i));

        Solution.main(null);
    }

    public double findMedianSortedArrays(int A[], int B[]) {
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
        /*
         * this part is not good due to inefficient in both space and time
         */
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

        int ma = (la + ra) / 2, mb = (lb + rb) / 2;
        boolean odda = ((ra - la + 1) % 2) == 1, oddb = ((rb - lb + 1) % 2) == 1;
        int min_rm;
        if (A[ma] >= B[mb]) {
            if (odda && oddb)
                min_rm = Math.min(ra-ma, mb-lb);
            else if (!odda && !oddb)
                /*
                 * actually if A[ma] >= B[mb] and size of B is even, we
                 * can drop B from lb to mb, inclusive.
                 */
                min_rm = Math.min(ra-ma-1, mb-lb+1);
            else
                min_rm = Math.min(ra-ma, mb-lb+1);
            return getMedian(A, la, ra - min_rm, B, lb + min_rm, rb);
        } else {
            if (odda && oddb)
                min_rm = Math.min(rb-mb, ma-la);
            else if (!odda && !oddb)
                min_rm = Math.min(rb-mb-1, ma-la+1);
            else
                min_rm = Math.min(rb-mb, ma-la+1);
            return getMedian(A, la + min_rm, ra, B, lb, rb - min_rm);
        }
    }
}