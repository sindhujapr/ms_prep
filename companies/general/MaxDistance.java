
/*
 * http://www.mitbbs.com/article_t1/JobHunting/32772225_0_1.html
 * Given an int array A[], define: distance=A[i]+A[j]+(j-i), j>=i. Find max distance in A[]?
 *
 * distance=A[i]+A[j]+(j-i) = (A[i]-i)+(A[j]+j). scan from left to right, save max(A[i]-i), one pass
 */
public class MaxDistance {
    public static int dist(int[] A) {
        // it seems that we don't need to track A[i]+i since there is constraint j >= i
        int max1 = A[0];
        int max = 2 * A[0];
        for(int i = 1; i < A.length; i++) {
            max = Math.max(Math.max(max, 2*A[i]), A[i]+i+max1);

            max1 = Math.max(max1, A[i]-i);
        }
        return max;
    }
}
