package interview.daiziguizhong;

import cern.colt.Arrays;

/*
 * http://chuansongme.com/n/142017
 */
public class CounterOfCombinationsSum {
	public int count(int[] num, int sum) {
		assert num != null;
		
		int n = num.length;
		int[][] dp = new int[n+1][sum+1];
		
		/*
		 * we must initialize dp[i][0] as 1, which means we always have the 
		 * (empty) combination if sum is 0. The analysis in the post is
		 * a bit flawed.
		 */
		for(int i = 0; i <= n; i++)
			dp[i][0] = 1;
		
		System.out.println(0 + "\t" + Arrays.toString(dp[0]));
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i-1][j];
				if(num[i-1] <= j)
					dp[i][j] += dp[i-1][j-num[i-1]];
			}
			
			System.out.println(i + "\t" + Arrays.toString(dp[i]));
		}
		
		return dp[n][sum];
	}
	
	public static void main(String[] args) {
		int[] num = {5, 5, 10, 2, 3};
		System.out.println(new CounterOfCombinationsSum().count(num, 15));
	}
}