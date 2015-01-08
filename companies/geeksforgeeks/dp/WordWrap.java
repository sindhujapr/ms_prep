package geeksforgeeks.dp;

import java.util.Arrays;

/*
 * Also named as text justification. See MIT OCW
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 */
public class WordWrap {
    public static void main(String[] args) {
        String[] words = {"fug", "we", "re", "sinab"};
        textJustification(words, 6);
    }
    
    public static void textJustification(String[] words, int L) {
        int n = words.length;
        int[][] extra = new int[n+1][n+1];
        int[][] lc = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++) {
            /*
             *  Better to use extra[i][i] to include words[i] so that the # of spaces
             *  can be counted easier.
             */
            extra[i][i] = L - words[i-1].length();
            for(int j = i+1; j <= n; j++)
                extra[i][j] = extra[i][j-1] - words[j-1].length() - 1;
        }
        
        for(int i = 1; i <= n; i++) {
            for(int j  = i; j <= n; j++) {
                if(extra[i][j] < 0)
                    lc[i][j] = -1;
                else if(extra[i][j] >= 0 && j == n)
                    lc[i][j] = 0;
                else
                    lc[i][j] = extra[i][j] * extra[i][j];
            }
        }
        
        int[] cost = new int[n+1];
        int[] parent = new int[n+1];
        for(int j = 1; j <= n; j++) {
            cost[j] = Integer.MAX_VALUE;
            for(int i = 1; i <= j; i++) {
                // here words[0, i-1] have been placed and words[i,j] will be placed in a separate line
                if(cost[i-1] >= 0 && lc[i][j] >= 0 && cost[i-1] + lc[i][j] < cost[j]) {
                    cost[j] = cost[i-1] + lc[i][j];
                    // The new line starts from i-1
                    parent[j] = i;
                }
            }
        }
        
        System.out.println("cost: " + cost[n]);
        System.out.println(Arrays.toString(parent));
        for(int i = n; i > 0; i = parent[i]-1)
            if(parent[i] >= 0)
                System.out.print((parent[i]-1) + " ");
    }
}