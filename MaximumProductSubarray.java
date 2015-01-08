public class MaximumProductSubarray {
	/*
	 * http://changhaz.wordpress.com/2014/09/23/leetcode-maximum-product-subarray/
	 */
	public int maxProduct(int[] A) {
		int maxi = 1, mini = 1;
		int res = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			// the previous result will be discarded if maxi is zero or negative
			int oldmaxi = Math.max(maxi, 1);
			if (A[i] > 0) {
				maxi = oldmaxi * A[i];
				mini = mini * A[i];
			} else {
				maxi = mini * A[i];
				mini = oldmaxi * A[i];
			}
			res = Math.max(res, maxi);
		}

		return res;
	}

	// http://yucoding.blogspot.com/2014/10/leetcode-quesion-maximum-product.html
    int maxProduct(int A[]) {
        int res = A[0];
        int maxp = A[0];
        int minp = A[0];
        for (int i = 1; i < A.length; i++){
            int tmpmax = maxp;
            int tmpmin = minp;
            maxp = Math.max(Math.max(tmpmax*A[i], tmpmin*A[i]), A[i]);
            minp = Math.min(Math.min(tmpmax*A[i], tmpmin*A[i]), A[i]);
            res = Math.max(maxp, res);
        }
        return res;
    }
}
