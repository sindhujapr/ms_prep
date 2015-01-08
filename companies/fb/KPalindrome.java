// http://www.careercup.com/question?id=6287528252407808
public class KPalindrome {
    public static boolean ModifiedEditDistance(String a, String b, int k) {
        int n = a.length();
        int dp[][] = new int[n+1][n+1];

		/*
		 * In java, we have to go through each array element to set it.
		 * This results in a time complexity of O(n^2). One solution is
		 * to only set the elements we need.
		 */
        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= n; j++)
                dp[i][j] = 100000;

        for (int i = 0 ; i <= n; i++)
            dp[i][0] = dp[0][i] = i;

        for (int i = 1; i <= n; i++) {
            int from = Math.max(1, i-k), to = Math.min(i+k, n);
            for (int j = from; j <= to; j++) {
                if (a.charAt(i-1) == b.charAt(j-1))           // same character
                    dp[i][j] = dp[i-1][j-1];
                // note that we don't allow letter substitutions

                dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j-1]); // delete character j
                dp[i][j] = Math.min(dp[i][j], 1 + dp[i-1][j]); // insert character i
            }
        }

        System.out.println(dp[n][n]);
        return dp[n][n] <= 2 * k;
    }
}
