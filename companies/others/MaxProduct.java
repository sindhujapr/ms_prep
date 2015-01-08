package test;

/**
 * Created by qingcunz on 10/5/14.
 */
public class MaxProduct {
    public static void main(String[] args) {
        System.out.println(new MaxProduct().maxProduct(new int[]{-2, -1, 3, -3}));
    }

    public int maxProduct(int[] A) {
        int maxi = 1, mini = 1;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            int oldmaxi = Math.max(maxi, 1);
            if (A[i] > 0) {
                maxi = oldmaxi * A[i];
                mini *= A[i];
            } else {
                maxi = mini * A[i];
                mini = oldmaxi * A[i];
            }
            res = Math.max(res, maxi);

            System.out.println(mini + "\t" + maxi + "\t" + res);
        }

        return res;
    }
}
