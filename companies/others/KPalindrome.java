package test;

/**
 * A k-palindrome is a string which transforms into a palindrome on removing at most k characters.

 Given a string S, and an interger K, print "YES" if S is a k-palindrome; otherwise print "NO".
 Constraints:
 S has at most 20,000 characters.
 0<=k<=30

 Sample Test Case#1:
 Input - abxa 1
 Output - YES
 Sample Test Case#2:
 Input - abdxa 1
 Output - No
 * Created by qingcunz on 11/9/14.
 */
public class KPalindrome {
    public static void main(String[] args) {
        int i = Integer.MAX_VALUE;
        System.out.println(i);
        i++;
        System.out.println(i + "\t" + Integer.MIN_VALUE);
        System.out.print(kpalin("abxa", 1));  // 2 <= 2*1 - YES
        System.out.print(kpalin("abdxa", 1)); // 4 > 2*1 - NO
        System.out.print(kpalin("abaxbabax", 2)); // 4 <= 2*2 - Y
    }

    public static boolean kpalin(String str, int K) {
        char[] chs = str.toCharArray();
        int n = chs.length;

        // matrix[i][i] and matrix[i][i+1] are automatically initialized to 0
        int[][] matrix = new int[n+1][n+1];

        for(int len = 2; len < n+1; len++) {
            for(int i = 0; i + len < n+1; i++) {
                int j = i+len;
                if(chs[i] == chs[j-1])
                    matrix[i][j] = matrix[i+1][j-1];
                else
                    matrix[i][j] = Math.min(matrix[i+1][j], matrix[i][j-1]) + 1;
            }
        }

        return matrix[0][n] <= K;
    }

    /*
     * http://www.careercup.com/question?id=6287528252407808
     *
     * http://www.quora.com/A-k-palindrome-is-a-string-which-transforms-into-a-palindrome-on-removing-at-most-k-characters-from-it-Given-a-string-S-and-an-interger-K-find-whether-S-is-a-k-palindrome-or-not-Constraints-S-has-at-most-20-000-characters-and-0-k-30
     *
     * It can be done O(n*k) time complexity. Basically we need to find if string S can be converted to it's reverse within 2*k insertion and deletion.

Let me explain with an example. Say the string (S) is aabca. Then reverse (S') is acbaa. After at most k deletion we want the string to be a palindrome i.e read same in backward direction i.e reverse. We can treat this as edit distance problem where only deletion and insertion are permitted.

aabca -> (delete) abca -> (insert) acbca -> (delete) acba -> (insert) acbaa

Note that, removing one character from string S actually translates to two operation here. So, we need to find out if S can be converted to S' within 2*k insertion deletion.

Since, we are permitted only k deletion, while comparing character of S and S', we do not need to look beyond k character (both backward and forward). This can be done in O(n*k) time complexity. Space complexity is O(k).
     */
    public static int ModifiedEditDistance(String a, int k) {
        String b = reverse(a);

        int i, j, n = a.length();
        // for simplicity. we should use only a window of size 2*k+1 or
        // dp[2][MAX] and alternate rows. only need row i-1
        int dp[][] = new int[n+1][n+1];
        for (i = 0 ; i < n; i++)
            dp[i][0] = dp[0][i] = i;

        for (i = 1; i <= n; i++) {
            int from = Math.max(1, i-k), to = Math.min(i+k, n);
            for (j = from; j <= to; j++) {
                if (a.charAt(i-1) == b.charAt(j-1))			// same character
                    dp[i][j] = dp[i-1][j-1];
                // note that we don't allow letter substitutions

                dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j-1]); // delete character j
                dp[i][j] = Math.min(dp[i][j], 1 + dp[i-1][j]); // insert character i
            }
        }
        return dp[n][n];
    }

    private static String reverse(String a) {
        if(a == null)
            return null;

        StringBuilder builder = new StringBuilder();
        for(char ch : a.toCharArray())
            builder.insert(0, ch);
        return builder.toString();
    }
}
