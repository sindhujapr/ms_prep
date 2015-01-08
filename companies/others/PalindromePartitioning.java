package test;

import java.util.List;

/**
 * Created by qingcunz on 10/30/14.
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]
 */

public class PalindromePartitioning {
    public static void main(String[] args) {
        int[][] res = new PalindromePartitioning().generateMatrix(3);
    }

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int val = 1, start = 0;
        while(val <= n*n) {
            // if(val == n * n) {
            //     res[start][start] = val;
            //     break;
            // }

            for(int j = start; j <= n-start-1; j++)
                res[start][j] = val++;
            for(int i = start+1; i <= n-start-1; i++)
                res[i][n-start-1] = val++;
            for(int j = n-start-2; j >= start; j--)
                res[n-start-1][j] = val++;
            for(int i = n-start-2; i > start; i--)
                res[i][start] = val++;

            start++;
        }
        return res;
    }

    public List<List<String>> partition(String s) {
        return null;
    }
}
