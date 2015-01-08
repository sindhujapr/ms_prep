package lc2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// see also http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
public class PalindromePartitioningII {
   public int minCut_based_on_minCut1_not_correct(String s) {
        int n = s.length();
        
        boolean ispalin[][] = new boolean[n][n];
		// length must be n+1 since we need dp[n]. See the code in the loop below
        int dp[] = new int[n+1];
        for(int i = 0; i <= n; i++)
            dp[i] = i;
        
        for(int i = 0; i < n; i++) {
            for(int j = i; j >= 0; j--) {
                if(s.charAt(i) == s.charAt(j) && (i-j <= 1 || ispalin[j+1][i-1])) {
                    ispalin[j][i] = true;
                    dp[i] = Math.min(dp[i], dp[j+1]+1);
                }
            }
        }

		return dp[n]-1;
	}

	public int minCut1(String s) {
		int n = s.length();
		if (n <= 1)
			return 0;
		boolean[][] isPalin = new boolean[n + 1][n + 1];

		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++)
			dp[i] = n - i;

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j <= n - 1; j++) {
				if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalin[i + 1][j - 1])) {
					isPalin[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j + 1] + 1);
				}
			}
		}
		return dp[0] - 1;
	}

	/*
	 * passes judge small/large
	 */
	public int minCut2(String s) {
		assert s != null;

		int length = s.length();
		boolean[][] palin = new boolean[length + 1][length + 1];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j <= Math.min(i, length - i - 1); j++) {
				if (s.charAt(i - j) == s.charAt(i + j))
					palin[i - j][i + j + 1] = true;
				else
					break;
			}

			for (int j = 0; j <= Math.min(i, length - i - 2); j++) {
				if (s.charAt(i - j) == s.charAt(i + j + 1))
					palin[i - j][i + j + 2] = true;
				else
					break;
			}
		}

		int dist[] = new int[length + 1];
		boolean visit[] = new boolean[length + 1];
		Arrays.fill(dist, length - 1);
		// we must initialize dist[i] if s.substring(0, i) is palindrome
		for (int i = 1; i <= length; i++)
			if (palin[0][i])
				dist[i] = 0;

		for (int i = 0; i < length; i++) {
			int min = 0;
			/*
			 * This is actually greedy algorithm:
			 * 
			 * Every time we pick the next index with the minimum cut so far and
			 * then update the distances for letters who formulate a palindrome
			 * with all letters before it but after the letter on the index.
			 */
			for (int j = 1; j <= length; j++)
				if (!visit[j] && (min == 0 || dist[j] < dist[min]))
					min = j;

			visit[min] = true;
			for (int j = min + 1; j <= length; j++) {
				if (!visit[j] && palin[min][j]) {
					dist[j] = Math.min(dist[j], dist[min] + 1);
					// We're sure this is the minimal cut
					if (j == length)
						return dist[j];
				}
			}
		}
		return dist[length];
	}

	Map<String, Integer> map = new HashMap<String, Integer>();

	/*
	 * doesn't pass large judge
	 */
	public int minCut3(String s) {
		if (map.get(s) != null)
			return map.get(s);

		if (isPalindrome(s)) {
			map.put(s, 0);
			return 0;
		}

		/*
		 * ret will be updated in each iteration of the loop: each time a
		 * smaller or equal value is assigned.
		 */
		int ret = s.length() - 1;
		for (int i = 1; i < s.length(); i++) {
			ret = Math.min(ret, minCut1(s.substring(0, i)) + 1 + minCut1(s.substring(i)));
		}

		map.put(s, ret);
		return ret;
	}

	/*
	 * also doesn't pass large judge
	 */
	public int minCut4(String s) {
		int len = s.length();
		int[][] data = new int[len][len];
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				if (i == j)
					data[i][j] = 0;
				else
					data[i][j] = j - i;

		/*
		 * the order of the below two loops cannot be changed, we need to make
		 * sure, say, data[i][i+3] is available when we evaluate data[i][i+4].
		 */
		for (int add = 1; add < len; add++)
			for (int i = 0; i + add < len; i++) {
				if (isPalindrome(s.substring(i, i + add + 1))) {
					data[i][i + add] = 0;
				} else {
					for (int d = 1; d <= add; d++) {
						data[i][i + add] = Math.min(data[i][i + add], data[i][i + d - 1]
								+ data[i + d][i + add] + 1);
					}
				}
			}
		return data[0][len - 1];
	}

	public boolean isPalindrome(String s) {
		return isPalindrome(s, 0, s.length() - 1);
	}

	public boolean isPalindrome(String s, int i, int j) {
		if (i > j)
			return true;

		if (s.charAt(i) != s.charAt(j))
			return false;

		return isPalindrome(s, i + 1, j - 1);
	}

	public static void main(String[] args) {
		PalindromePartitioningII instance = new PalindromePartitioningII();
		// System.out.println(instance
		// .minCut3("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk"));
		// System.out.println(instance
		// .minCut3("eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj"));

		System.out.println(instance.minCut2("cabababcbc"));
	}
}
