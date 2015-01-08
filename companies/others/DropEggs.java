package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 11/14/14.
 */
public class DropEggs {
    public static void main(String[] args) {
        List<List<Integer>> res = permute(new int[]{1, 3, 2});
        for(List<Integer> list : res)
            System.out.println(list);
    }

    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num == null || num.length == 0)
            return res;

        res.add(new ArrayList<Integer>());
        for(int i = 0; i < num.length; i++) {
            // insert num[i] into the existing res and generate new
            List<List<Integer>> temp = new ArrayList<List<Integer>>();
            for(int j = 0; j < res.size(); j++) {
                for (int k = 0; k <= res.get(j).size(); k++) {
                    List<Integer> list = new ArrayList<Integer>(res.get(j));

                    list.add(k, num[i]);
                    temp.add(list);
                }
            }

            res = temp;
        }
        return res;
    }

    public static int drops(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i <= n; i++)
            dp[i][0] = 0;
        for(int j = 0; j <= k; j++)
            dp[0][j] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                dp[i][j] =
                        dp[i][j] = Math.min(Math.max(dp[i-1][j], dp[i][k-j]) + 1, dp[i][j]);
            }
        }

        return dp[n][k];
    }
}
